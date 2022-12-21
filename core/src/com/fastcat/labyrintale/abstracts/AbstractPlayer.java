package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.prototype.GameConfiguration;
import com.fastcat.labyrintale.prototype.providers.PlayerStatProvider;
import com.fastcat.labyrintale.prototype.tracker.Tracker;
import com.fastcat.labyrintale.skills.player.basic.MoveP;
import com.fastcat.labyrintale.skills.player.basic.PassTurn;
import com.fastcat.labyrintale.strings.CharString;

public abstract class AbstractPlayer extends AbstractEntity {

    public final PlayerJob job;
    public final PlayerClass playerClass;
    public final Sprite camp;
    public final Sprite upset;

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

        CharString.CharData temp = StringHandler.charString.get(id);
        name = temp.NAME;
        desc = temp.DESC;
        pColor = c.cpy();
        move = new MoveP(this);
        pass = new PassTurn(this);
        setImage(
                FileHandler.getCharImg().get(playerClass),
                FileHandler.getCharImgTurn().get(playerClass),
                FileHandler.getCharBgImg().get(playerClass));
        camp = FileHandler.getCharCampImg().get(playerClass);
        upset = FileHandler.getCharUpsetImg().get(playerClass);
        imgPanel = FileHandler.getCharPanelImg().get(playerClass);
        Array<AbstractItem> t = getStartingItem();
        for (int j = 0; j < 2; j++) {
            AbstractItem it = t.get(j);
            item[j] = it;
            it.onGain();
        }
        passive = getPassive();
        passive.onGain();
        if (GameConfiguration.getInstance().hasProvider(PlayerStatProvider.class)) {
            PlayerStatProvider provider = GameConfiguration.getInstance().getProvider(PlayerStatProvider.class);
            provider.apply(this);
            provider.addTracker(new Tracker<>(this));
        }
        stat.neutRes = 50;
    }

    public static String getClassName(PlayerClass playerClass) {
        return playerClass.toString().toLowerCase();
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
        GUARD, ATTACKER, SUPPORTER
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
