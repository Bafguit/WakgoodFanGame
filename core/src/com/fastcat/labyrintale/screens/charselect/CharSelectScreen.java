package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.control.InfoPanel;

import static com.fastcat.labyrintale.handlers.FileHandler.bg;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class CharSelectScreen extends AbstractScreen {

    public CharSelectText charSelectText;
    public BackButton backButton;
    public NextToAdvisorButton nextButton;
    public SeedText seedText;
    public CharSelectGroup group;
    public AbstractPlayer selected;
    public CharButton[] chars = new CharButton[4];
    public CharButton[] aChars = new CharButton[8];

    public CharSelectScreen() {
        setBg(bg.get("BG_CHARSELECT"));
        charSelectText = new CharSelectText();
        backButton = new BackButton();
        nextButton = new NextToAdvisorButton();
        seedText = new SeedText();
        group = new CharSelectGroup();
        CharButton char1 = new CharButton();
        CharButton char2 = new CharButton();
        CharButton char3 = new CharButton();
        CharButton char4 = new CharButton();
        char1.setPosition(Gdx.graphics.getWidth() * 0.3725f - char1.sWidth / 2, Gdx.graphics.getHeight() * 0.95f - char1.height / 2);
        char2.setPosition(Gdx.graphics.getWidth() * 0.4575f - char2.sWidth / 2, Gdx.graphics.getHeight() * 0.95f - char2.height / 2);
        char3.setPosition(Gdx.graphics.getWidth() * 0.5425f - char3.sWidth / 2, Gdx.graphics.getHeight() * 0.95f - char3.height / 2);
        char4.setPosition(Gdx.graphics.getWidth() * 0.6275f - char4.sWidth / 2, Gdx.graphics.getHeight() * 0.95f - char4.height / 2);
        chars[0] = char1;
        chars[1] = char2;
        chars[2] = char3;
        chars[3] = char4;
        addChars();
    }

    private void addChars() {
        AbstractPlayer.PlayerClass[] pc = AbstractPlayer.PlayerClass.values();
        for(int i = 0; i < 8; i++) {
            CharButton char0 = new CharButton(AbstractLabyrinth.getPlayerInstance(pc[i]));
            char0.setPosition(Gdx.graphics.getWidth() * 0.21f + Gdx.graphics.getWidth() * 0.085f * i - char0.sWidth / 2, Gdx.graphics.getHeight() * 0.15f - char0.height / 2);
            aChars[i] = char0;
        }
    }

    @Override
    public void update() {
        int cc = 0;
        for(int i = 0; i < 4; i++) {
            CharButton tc = chars[i];
            tc.update();
            if(tc.isOnLock) cc++;
        }
        for(int i = 0; i < 8; i++) {
            aChars[i].update();
        }

        if(cc == 4) {
            nextButton.enable();
        }
        else {
            nextButton.disable();
        }
        if(selected != null) group.update();

        seedText.update();
        backButton.update();
        nextButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if(selected != null) group.render(sb);
        charSelectText.render(sb);
        for (CharButton aChar : chars) {
            aChar.render(sb);
        }
        for(int i = 0; i < 8; i++) {
            aChars[i].render(sb);
        }

        seedText.render(sb);
        backButton.render(sb);
        nextButton.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (CharButton aChar : chars) {
            aChar.dispose();
        }
        for(int i = 0; i < 8; i++) {
            aChars[i].dispose();
        }
    }

    public static class CharSelectGroup {

        private final FontHandler.FontData nData = TURN_CHANGE;
        private final FontHandler.FontData dData = FontHandler.CARD_BIG_NAME;
        private final FontHandler.FontData inData = FontHandler.CARD_BIG_NAME;
        private final FontHandler.FontData idData = FontHandler.CARD_BIG_DESC;

        public InfoPanel.InfoType type;
        public AbstractPlayer player;
        public AbstractItem item;
        public AbstractSkill skill;
        public HealthIcon health;
        public CharInfoItemButton[] items = new CharInfoItemButton[2];
        public CharInfoItemButton[] skills = new CharInfoItemButton[3];
        public float x, ny, dy, iny, idy, bgx, bgy;
        public float tw, cw = 0, ch = 0;

        public CharSelectGroup() {
            type = InfoPanel.InfoType.COLOR;
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            health = new HealthIcon();
            health.setPosition(w * 0.77f, h * 0.635f);
            for(int i = 0; i < 2; i++) {
                CharInfoItemButton b = new CharInfoItemButton(this);
                b.setPosition(w * 0.515f + w * 0.06f * i, h * 0.43f);
                items[i] = b;
            }
            for(int i = 0; i < 3; i++) {
                CharInfoItemButton b = new CharInfoItemButton(this);
                b.setPosition(w * 0.655f + w * 0.06f * i, h * 0.43f);
                skills[i] = b;
            }
            tw = w * 0.34f;
            x = w * 0.515f;
            bgx = w * 0.175f;
            bgy = h * -0.15f;
            ny = h * 0.715f;
            dy = h * 0.625f;
            iny = h * 0.4f;
            idy = h * 0.35f;
        }

        public void setPlayer(AbstractPlayer player) {
            this.player = player;
            health.setHealth(player.maxHealth);
            for(int i = 0; i < 2; i++) {
                items[i].setItem(player.item[i]);
            }
            for(int i = 0; i < 3; i++) {
                skills[i].setSkill(player.deck.get(i));
            }
            cw = player.bg.getWidth() * InputHandler.scale;
            ch = player.bg.getHeight() * InputHandler.scale;
        }

        public void update() {
            type = InfoPanel.InfoType.COLOR;
            for(int i = 0; i < 2; i++) {
                items[i].update();
            }
            for(int i = 0; i < 3; i++) {
                skills[i].update();
            }
        }

        public void render(SpriteBatch sb) {
            sb.draw(player.bg, bgx, bgy, cw, ch);
            renderLineBotLeft(sb, nData, player.name, x, ny, tw, 0);
            renderColorLeft(sb, dData, player.desc, x, dy, tw);

            health.render(sb);
            for(int i = 0; i < 2; i++) {
                items[i].render(sb);
            }
            for(int i = 0; i < 3; i++) {
                skills[i].render(sb);
            }
            if(type == InfoPanel.InfoType.SKILL) {
                renderLineBotLeft(sb, inData, skill.name, x, iny, tw, 0);
                renderCardLeft(sb, skill, idData, skill.desc, x, idy, tw, 0);
            } else if(type == InfoPanel.InfoType.ITEM) {
                renderLineBotLeft(sb, inData, item.name, x, iny, tw, 0);
                renderColorLeft(sb, idData, item.desc, x, idy, tw);
            }
        }
    }

    public static class HealthIcon extends AbstractUI {

        public int health = 0;
        public Sprite hImg;

        public HealthIcon() {
            super(FileHandler.ui.get("BORDER_M"));
            fontData = ENERGY;
            hImg = FileHandler.ui.get("HEART");
            overable = false;
        }

        @Override
        public void render(SpriteBatch sb) {
            if(enabled) {
                sb.setColor(Color.WHITE);
                sb.draw(hImg, x, y, sWidth, sHeight);
                renderCenter(sb, fontData, Integer.toString(health), x, y + sHeight / 2, sWidth, sHeight);
            }
        }

        public void setHealth(int a) {
            health = a;
        }
    }

    public static class CharInfoItemButton extends AbstractUI {

        public InfoPanel.InfoType type = InfoPanel.InfoType.COLOR;
        public AbstractSkill skill;
        public AbstractItem item;
        public CharSelectGroup group;
        public String name;
        public String desc;

        public CharInfoItemButton(CharSelectGroup group) {
            super(FileHandler.ui.get("BORDER"));
            this.group = group;
        }

        public void setSkill(AbstractSkill skill) {
            this.skill = skill;
            type = InfoPanel.InfoType.SKILL;
        }

        public void setItem(AbstractItem item) {
            this.item = item;
            type = InfoPanel.InfoType.ITEM;
        }

        @Override
        public void updateButton() {
            if(over) {
                group.type = type;
                group.item = item;
                group.skill = skill;
            }
        }

        @Override
        public void render(SpriteBatch sb) {
            if(enabled) {
                sb.setColor(Color.WHITE);
                if(type != InfoPanel.InfoType.COLOR) {
                    sb.draw(type == InfoPanel.InfoType.SKILL ? skill.img : item.img, x, y, sWidth, sHeight);
                }
                sb.draw(img, x, y, sWidth, sHeight);

                if(fontData != null) {
                    renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
                }
            }
        }
    }
}
