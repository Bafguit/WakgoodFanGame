package com.fastcat.labyrintale.screens.charselect;

import static com.badlogic.gdx.graphics.Color.DARK_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.sc;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.TutorialButton;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import com.fastcat.labyrintale.uis.control.InfoPanel;

import java.util.HashMap;

public class CharSelectScreen extends AbstractScreen {

    public CharSelectText charSelectText;
    public BackButton backButton;
    public StartButton nextButton;
    public SeedText seedText;
    public CharSelectGroup group;
    public AbstractLabyrinth.Difficulty diff = AbstractLabyrinth.Difficulty.NORMAL;
    public AbstractPlayer selected;
    public TutorialButton tuto;
    public AbstractUI.TempUI ground;
    public AbstractUI.TempUI buttonLine;
    public CharButton[] chars = new CharButton[4];
    public CharButton[] aChars = new CharButton[8];

    public CharSelectScreen() {
        setBg(FileHandler.getBg().get("BG_CHARSELECT"));
        charSelectText = new CharSelectText();
        backButton = new BackButton();
        nextButton = new StartButton();
        seedText = new SeedText();
        group = new CharSelectGroup();
        CharButton char1 = new CharButton();
        CharButton char2 = new CharButton();
        CharButton char3 = new CharButton();
        CharButton char4 = new CharButton();
        float cy = 40 * scale;
        char1.setPosition(776 * scale, cy);
        char2.setPosition(981 * scale, cy);
        char3.setPosition(1186 * scale, cy);
        char4.setPosition(1391 * scale, cy);
        nextButton.setPosition(1596 * scale, cy);
        chars[0] = char1;
        chars[1] = char2;
        chars[2] = char3;
        chars[3] = char4;
        addChars();
        tuto = new TutorialButton(FileHandler.getUi().get("CHAR_TUTO"), TutorialScreen.TutorialType.CHARSELECT);
        tuto.isPixmap = true;
        tuto.setPosition(606 * scale, 78 * scale);
        ground = new AbstractUI.TempUI(FileHandler.getUi().get("CHAR_GROUND"));
        ground.setPosition(0, 0);
        buttonLine = new AbstractUI.TempUI(FileHandler.getUi().get("CHAR_LINE_B"));
        buttonLine.setPosition(Gdx.graphics.getWidth() * 0.5f - buttonLine.sWidth / 2, 133 * scale);
        cType = ControlPanel.ControlType.HIDE;
    }

    private void addChars() {
        AbstractPlayer.PlayerClass[] pc = AbstractPlayer.PlayerClass.values();
        for (int i = 0; i < 8; i++) {
            CharButton char0 = new CharButton(AbstractLabyrinth.getPlayerInstance(pc[i]));
            char0.setPosition(
                    (467 + 205 * i) * scale,
                    175 * scale);
            aChars[i] = char0;
        }
    }

    @Override
    public void update() {
        int cc = 0;
        for (int i = 0; i < 4; i++) {
            CharButton tc = chars[i];
            tc.update();
            if (tc.isOnLock) cc++;
        }
        for (int i = 0; i < 8; i++) {
            aChars[i].update();
        }

        nextButton.overable = cc == 4;
        if (selected != null) group.update();
        tuto.update();

        seedText.update();
        backButton.update();
        nextButton.update();
        if (!Labyrintale.fading && InputHandler.cancel) {
            backButton.onClick();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (selected != null) group.render(sb);
        ground.render(sb);
        //charSelectText.render(sb);
        buttonLine.render(sb);
        for (CharButton aChar : chars) {
            aChar.render(sb);
        }
        for (int i = 0; i < 8; i++) {
            aChars[i].render(sb);
        }
        tuto.render(sb);

        seedText.render(sb);
        backButton.render(sb);
        nextButton.render(sb);
    }

    @Override
    public void show() {
        backButton.over = false;
        backButton.show();
        if (SettingHandler.setting.charTutorial) {
            Labyrintale.openTutorial(TutorialScreen.TutorialType.CHARSELECT);
            SettingHandler.setting.charTutorial = false;
            SettingHandler.save();
            AbstractPlayer p = aChars[0].player;
            selected = p;
            group.setPlayer(p);
        }
    }

    @Override
    public void hide() {
        selected = null;
    }

    @Override
    public void dispose() {
        for (CharButton aChar : chars) {
            aChar.dispose();
        }
        for (int i = 0; i < 8; i++) {
            aChars[i].dispose();
        }
    }

    public static class CharSelectGroup {

        private final FontHandler.FontData nData = CS_NAME;
        private final FontHandler.FontData inData = INFO_HP;
        private final FontHandler.FontData idData = GOMEM;
        private final FontHandler.FontData jData = SETTING;
        private final HashMap<AbstractPlayer.PlayerClass, Integer> sIndex;

        public InfoPanel.InfoType type;
        public AbstractPlayer player;
        public AbstractItem item;
        public AbstractSkill skill;
        public HealthIcon health;
        public CharStatIcon[] stats;
        public SkinLeft left;
        public SkinRight right;
        public Array<AbstractPlayer.CustomSkinData> skins;
        public AbstractUI.TempUI job;
        public AbstractUI.TempUI charLine;
        public CharInfoItemButton passive;
        public CharInfoItemButton[] skills = new CharInfoItemButton[3];
        public float x, nx, ny, iny, idy, bgx, bgy;
        public float tw, cw = 0, ch = 0;
        public int index = -1;

        public CharSelectGroup() {
            type = InfoPanel.InfoType.COLOR;
            health = new HealthIcon();
            health.setPosition(1535 * scale, 830 * scale);
            passive = new CharInfoItemButton(this);
            passive.setPosition(1700 * scale, 840 * scale);
            for (int i = 0; i < 3; i++) {
                CharInfoItemButton b = new CharInfoItemButton(this);
                b.setPosition((1560 + 154 * i) * scale, 455 * scale);
                skills[i] = b;
            }
            stats = new CharStatIcon[6];
            for (int i = 0; i < 6; i++) {
                float cw = 1560 * scale, ch = (800 - 40 * i) * scale;
                CharStatIcon c = new CharStatIcon(StatIcon.StatType.values()[i + 2]);
                c.setPosition(cw, ch);
                stats[i] = c;
            }
            tw = 400 * scale;
            x = 2015 * scale;
            bgx = 506 * scale;
            bgy = -40 * scale;
            nx = 1796 * scale;
            ny = 1150 * scale;
            iny = 585 * scale;
            idy = 540 * scale;
            charLine = new AbstractUI.TempUI(FileHandler.getUi().get("CHAR_LINE"));
            charLine.setPosition(1474 * scale, 1076 * scale);
            job = new AbstractUI.TempUI(FileHandler.getUi().get("JOB_DEF"));
            job.setPosition(1571 * scale, 1103 * scale);
            skins = new Array<>();
            left = new SkinLeft(this);
            left.setScale(2);
            left.setPosition(347 * scale, 735 * scale);
            left.enabled = skins.size > 0;
            right = new SkinRight(this);
            right.setScale(2);
            right.setPosition(1248 * scale, 735 * scale);
            right.enabled = skins.size > 0;
            sIndex = new HashMap<>();
        }

        public void setPlayer(AbstractPlayer player) {
            this.player = player;
            health.setHealth(player.maxHealth);
            passive.setItem(player.passive);
            for (int i = 0; i < 3; i++) {
                skills[i].setSkill(player.deck.get(i));
            }
            for (int i = 0; i < 6; i++) {
                stats[i].setEntity(player);
            }
            cw = player.bg.getWidth() * scale;
            ch = player.bg.getHeight() * scale;
            job.img = FileHandler.getUi().get("JOB_" + player.job);
            skins = new Array<>();
            for(AbstractPlayer.CustomSkinData d : CustomHandler.skins.get(player.playerClass).values()) {
                skins.add(d);
            }
            boolean sk = skins.size > 0;
            if(!sk) index = -1;
            else {
                if(sIndex.get(player.playerClass) == null) {
                    String s = SettingHandler.setting.skin.get(player.playerClass);
                    index = -1;
                    if (!s.equals("basic")) {
                        for (int i = 0; i < skins.size; i++) {
                            AbstractPlayer.CustomSkinData d = skins.get(i);
                            if (d.key.equals(s)) {
                                index = i;
                                break;
                            }
                        }
                    }
                    sIndex.put(player.playerClass, index);
                } else {
                    index = sIndex.get(player.playerClass);
                }
            }
            refreshSkin();
            left.enabled = sk;
            right.enabled = sk;
        }

        @SuppressWarnings("NewApi")
        public void refreshSkin() {
            if(index > -1) player.setCustomSkin(skins.get(index).key);
            else player.setBasicSkin();
            sIndex.replace(player.playerClass, index);
        }

        public void update() {
            type = InfoPanel.InfoType.COLOR;
            passive.update();
            for (int i = 0; i < 3; i++) {
                skills[i].update();
            }
            for (int i = 0; i < 6; i++) {
                stats[i].update();
            }
            left.update();
            right.update();
        }

        public void render(SpriteBatch sb) {
            sb.setColor(Color.WHITE);
            sb.draw(player.bg, bgx, bgy, cw, ch);
            left.render(sb);
            right.render(sb);
            job.render(sb);
            renderLineLeft(sb, nData, player.name, nx, ny, ny, 0);
            String j;
            if(player.job == AbstractPlayer.PlayerJob.DEF) j = "수호자";
            else if(player.job == AbstractPlayer.PlayerJob.ATK) j = "타격대";
            else j = "지원가";
            renderLineLeft(sb, jData, j, nx, ny + 70 * scale, ny, 0);
            renderColorLeft(sb, inData, player.desc, 1560 * scale, ny - 85 * scale, 700 * scale);
            charLine.render(sb);

            health.render(sb);
            passive.render(sb);
            renderColorLeft(sb, idData, passive.item.desc, passive.x + passive.sWidth * 1.1f, passive.y + passive.sHeight, 500 * scale);
            for (int i = 0; i < 3; i++) {
                skills[i].render(sb);
            }
            if (type == InfoPanel.InfoType.SKILL) {
                renderLineBotLeft(sb, inData, skill.name, x, iny, tw, 0);
                renderCardLeft(sb, skill, idData, skill.desc, x, idy, tw, 0);
            }
            for (int i = 0; i < 6; i++) {
                stats[i].render(sb);
            }
        }
    }

    private static class SkinLeft extends AbstractUI {

        protected CharSelectGroup group;

        public SkinLeft(CharSelectGroup group) {
            super(FileHandler.getUi().get("LEFT"));
            this.group = group;
            clickable = group.index > -1;
        }

        @Override
        protected void updateButton() {
            clickable = group.index > -1;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                if(!clickable) sb.setColor(DARK_GRAY);
                else if (!over) sb.setColor(Color.LIGHT_GRAY);
                else sb.setColor(WHITE);
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }

        @Override
        public void onClick() {
            group.index--;
            group.refreshSkin();
        }
    }

    private static class SkinRight extends AbstractUI {

        protected CharSelectGroup group;

        public SkinRight(CharSelectGroup group) {
            super(FileHandler.getUi().get("RIGHT"));
            this.group = group;
            clickable = group.index < (group.skins.size - 1);
        }

        @Override
        protected void updateButton() {
            clickable = group.index < (group.skins.size - 1);
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                if(!clickable) sb.setColor(DARK_GRAY);
                else if (!over) sb.setColor(Color.LIGHT_GRAY);
                else sb.setColor(WHITE);
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }

        @Override
        public void onClick() {
            group.index++;
            group.refreshSkin();
        }
    }

    public static class HealthIcon extends AbstractUI {

        public int health = 0;
        public Sprite hImg;

        public HealthIcon() {
            super(FileHandler.getUi().get("BORDER_M"));
            fontData = ENERGY;
            hImg = FileHandler.getUi().get("HEART");
            overable = false;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
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

        private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
        public InfoPanel.InfoType type = InfoPanel.InfoType.COLOR;
        public AbstractSkill skill;
        public AbstractItem item;
        public CharSelectGroup group;
        public String name;
        public String desc;

        public CharInfoItemButton(CharSelectGroup group) {
            super(FileHandler.getUi().get("BORDER"));
            this.group = group;
            fontData = FontHandler.SUB_NAME;
            clickable = false;
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
        protected Array<SubText> getSubText() {
            if (type == InfoPanel.InfoType.SKILL) return skill.key;
            else if (type == InfoPanel.InfoType.ITEM) return item.key;
            else return new Array<>();
        }

        @Override
        public void updateButton() {
            if (over && type == InfoPanel.InfoType.SKILL) {
                group.type = type;
                group.item = item;
                group.skill = skill;
            }
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                sb.setColor(Color.WHITE);
                if (type != InfoPanel.InfoType.COLOR) {
                    if (type == InfoPanel.InfoType.SKILL) {
                        sb.draw(skill.img, x, y, sWidth, sHeight);
                        sb.draw(img, x, y, sWidth, sHeight);
                        if (!skill.passive) {
                            sb.draw(cost, x - sWidth * 0.05f, y + sWidth * 0.65f, sWidth * 0.4f, sWidth * 0.4f);
                            FontHandler.renderCenter(
                                    sb,
                                    fontData,
                                    Integer.toString(skill.cost),
                                    x + sWidth * 0.05f,
                                    y + sWidth * 0.85f,
                                    sWidth * 0.2f,
                                    sWidth * 0.2f);
                        }
                    } else {
                        sb.draw(item.img, x, y, sWidth, sHeight);
                        if (item.rarity != AbstractItem.ItemRarity.STARTER) sb.draw(img, x, y, sWidth, sHeight);
                    }
                }
            }
        }
    }
}
