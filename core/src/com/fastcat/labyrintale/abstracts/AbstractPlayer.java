package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.CustomHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.skills.player.basic.MoveP;
import com.fastcat.labyrintale.skills.player.basic.PassTurn;
import com.fastcat.labyrintale.strings.CharString;
import com.fastcat.labyrintale.utils.SpineAnimation;
import com.fastcat.labyrintale.utils.SpriteAnimation;

import java.util.HashMap;

public abstract class AbstractPlayer extends AbstractEntity {

    public HashMap<String, CustomSkinData> skins;
    public String key;

    public final PlayerJob job;
    public final PlayerClass playerClass;
    public final Sprite select;
    public Sprite camp;
    public Sprite upset;

    public AbstractPlayer(String id, int maxHealth, Color c, PlayerJob job) {
        super(
                id,
                3,
                maxHealth,
                FileHandler.getAtlas().get(id),
                FileHandler.getSkeleton().get(id),
                true);
        this.job = job;
        this.playerClass = PlayerClass.valueOf(id.toUpperCase());
        skins = CustomHandler.skins.get(playerClass);

        for(CustomSkinData skin : skins.values()) {
            skin.animation.resetAnimation();
        }

        pColor = c.cpy();
        move = new MoveP(this);
        pass = new PassTurn(this);

        setBasicSkin();

        select = FileHandler.getCharSelectImg().get(playerClass);
        Array<AbstractItem> t = getStartingItem();
        for (int j = 0; j < 2; j++) {
            AbstractItem it = t.get(j);
            item[j] = it;
            it.onGain();
        }
        passive = getPassive();
        passive.onGain();
        stat.neutRes = 50;
    }

    @Override
    public void newDeck() {
        hand = new AbstractSkill[3];
        for (int i = 0; i < 3; i++) {
            hand[i] = deck.get(i).clone();
        }
        moveTemp = move.clone();
        pass = new PassTurn(this);
    }

    public void setDummy() {
        dummy = true;
        isDead = true;
    }

    public void gainItem(AbstractItem i, int index) {
        item[index].onRemove();
        i.owner = this;
        item[index] = i;
        item[index].onGain();
    }

    public boolean hasSlot() {
        int i = AbstractLabyrinth.maxSkillUp;
        return deck.get(0).upgradeCount < i || deck.get(1).upgradeCount < i || deck.get(2).upgradeCount < i;
    }

    public abstract Array<AbstractItem> getStartingItem();

    public abstract AbstractItem getPassive();

    public static Sprite getPlayerPortrait(AbstractPlayer p) {
        return FileHandler.getCharImg().get(p.playerClass);
    }

    public static Sprite getPlayerPortrait(PlayerClass p) {
        return FileHandler.getCharImg().get(p);
    }

    public enum PlayerJob {
        DEF, ATK, SUP
    }

    public void setBasicSkin() {
        CharString.CharData temp = StringHandler.charString.get(id);
        key = "basic";
        name = temp.NAME;
        desc = temp.DESC;
        setImage(
                FileHandler.getCharImg().get(playerClass),
                FileHandler.getCharImgTurn().get(playerClass),
                FileHandler.getCharBgImg().get(playerClass));
        camp = FileHandler.getCharCampImg().get(playerClass);
        upset = FileHandler.getCharUpsetImg().get(playerClass);
        imgPanel = FileHandler.getCharPanelImg().get(playerClass);
        animation = new SpineAnimation(atlas, skeleton);
    }

    public void setCustomSkin(String key) {
        CustomSkinData d = skins.get(key);
        if(d != null) {
            CustomSkinData data = d.cpy();
            this.key = key;
            name = data.name;
            desc = data.desc;
            setImage(data.portrait, data.turn, data.bg);
            camp = data.camp;
            upset = data.upset;
            imgPanel = data.panel;
            animation = data.animation;
        }
    }

    public void setTempCustomSkin(String key) {
        CustomSkinData data = skins.get(key);
        if(data != null) {
            this.key = key;
            name = data.name;
            desc = data.desc;
            setImage(data.portrait, data.turn, data.bg);
            camp = data.camp;
            upset = data.upset;
            imgPanel = data.panel;
            animation = data.animation;
        }
    }

    public static class CustomSkinData {
        public final String key;
        public final String name;
        public final String desc;
        public final PlayerClass playerClass;
        public final Sprite portrait;
        public final Sprite bg;
        public final Sprite camp;
        public final Sprite upset;
        public final Sprite panel;
        public final Sprite turn;
        public final SkinType type;
        public final AbstractAnimation animation;

        public CustomSkinData(PlayerClass p) {
            type = SkinType.CLASS;
            this.key = "coffin";
            String player = p.name().toLowerCase();
            JsonValue j = FileHandler.generateJson(Gdx.files.internal("spine/" + player + "/coffin/config.json"));
            playerClass = PlayerClass.valueOf(j.get("class").asString());
            name = j.get("name").asString();
            desc = j.get("desc").asString();

            String type = j.get("type").asString();
            if(type.equals("sprite")) {
                animation = new SpriteAnimation(Gdx.files.internal("spine/" + player + "/coffin/assets/animation"));
            } else {
                FileHandle s = Gdx.files.internal("spine/" + player + "/coffin/assets/animation/skeleton.json");
                TextureAtlas a = new TextureAtlas(Gdx.files.internal("spine/" + player + "/coffin/assets/animation/skeleton.atlas"));
                animation = new SpineAnimation(a, s);
            }

            Texture t1 = new Texture(Gdx.files.internal("spine/" + player + "/coffin/assets/portrait.png"));
            t1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            portrait = new Sprite(t1);

            Texture t2 = new Texture(Gdx.files.internal("spine/" + player + "/coffin/assets/bg.png"));
            t2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            bg = new Sprite(t2);

            Texture t3 = new Texture(Gdx.files.internal("spine/" + player + "/coffin/assets/camp_0.png"));
            t3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            camp = new Sprite(t3);

            Texture t4 = new Texture(Gdx.files.internal("spine/" + player + "/coffin/assets/camp_1.png"));
            t4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            upset = new Sprite(t4);

            Texture t5 = new Texture(Gdx.files.internal("spine/" + player + "/coffin/assets/panel.png"));
            t5.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            panel = new Sprite(t5);

            Texture t6 = new Texture(Gdx.files.internal("spine/" + player + "/coffin/assets/turn.png"));
            t6.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            turn = new Sprite(t6);
        }

        public CustomSkinData(String key) {
            type = SkinType.KEY;
            this.key = key;

            JsonValue j = FileHandler.generateJson(Gdx.files.local("custom/" + key + "/config.json"));
            playerClass = PlayerClass.valueOf(j.get("class").asString());
            name = j.get("name").asString();
            desc = j.get("desc").asString();

            String type = j.get("type").asString();
            if(type.equals("sprite")) {
                animation = new SpriteAnimation(Gdx.files.local("custom/" + key + "/assets/animation"));
            } else {
                FileHandle s = Gdx.files.local("custom/" + key + "/assets/animation/skeleton.json");
                TextureAtlas a = new TextureAtlas(Gdx.files.local("custom/" + key + "/assets/animation/skeleton.atlas"));
                animation = new SpineAnimation(a, s);
            }

            Texture t1 = new Texture(Gdx.files.local("custom/" + key + "/assets/portrait.png"));
            t1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            portrait = new Sprite(t1);

            Texture t2 = new Texture(Gdx.files.local("custom/" + key + "/assets/bg.png"));
            t2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            bg = new Sprite(t2);

            Texture t3 = new Texture(Gdx.files.local("custom/" + key + "/assets/camp_0.png"));
            t3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            camp = new Sprite(t3);

            Texture t4 = new Texture(Gdx.files.local("custom/" + key + "/assets/camp_1.png"));
            t4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            upset = new Sprite(t4);

            Texture t5 = new Texture(Gdx.files.local("custom/" + key + "/assets/panel.png"));
            t5.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            panel = new Sprite(t5);

            Texture t6 = new Texture(Gdx.files.local("custom/" + key + "/assets/turn.png"));
            t6.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            turn = new Sprite(t6);
        }

        public CustomSkinData cpy() {
            if(type == SkinType.CLASS) return new CustomSkinData(playerClass);
            else return new CustomSkinData(key);
        }

        enum SkinType {
            CLASS,
            KEY
        }
    }

    public enum PlayerClass {
        WAK,
        INE,
        BURGER,
        LILPA,
        JURURU,
        GOSEGU,
        VIICHAN,
        MANAGER
    }
}
