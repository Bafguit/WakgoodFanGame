package com.fastcat.labyrintale.uis.control;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;
import static com.fastcat.labyrintale.uis.control.InfoPanel.InfoType.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.reward.RewardScreen;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;
import com.fastcat.labyrintale.uis.*;

public class InfoPanel extends AbstractUI {

    public Sprite border = FileHandler.getUi().get("BORDER_B");
    public String name = "";
    public String desc = "";
    public FontHandler.FontData fontName = PANEL_NAME;
    public FontHandler.FontData fontDesc = PANEL_DESC;
    public PlayerIcon[] pIcons = new PlayerIcon[4];
    public ControlPanel cp;
    public MapButton map;
    public TempUI gold;
    public SettingButton setting;
    public TutorialButton tutorial;
    public PlayerInfoButton playerInfo;
    public InfoType type = InfoType.COLOR;
    public AbstractSkill skill;
    public AbstractStatus status;
    public AbstractItem item;
    public ItemPanel aSkill;
    public AbstractEntity player;
    public boolean show;
    public boolean renderIcon = true;
    public float nx, ny, nw, nh, dx, dy, dw, dh, fy;
    public float bnx, bny, bnw, bnh, bdx, bdy, bdw, bdh;

    public InfoPanel(ControlPanel cp) {
        super(FileHandler.getUi().get("BORDER_B"));
        this.cp = cp;
        clickable = false;
        overable = false;
        float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
        setPosition(w * 0.5f, h * 0.26f - sHeight / 2);
        nx = dx = w * 0.615f;
        ny = h * 0.335f;
        dy = h * 0.325f - 50 * scale;
        nw = dw = 880 * InputHandler.scale;
        nh = 60 * InputHandler.scale;
        dh = 280 * InputHandler.scale;

        bnx = 970 * scale;
        bny = 445 * scale;
        bdx = 1000 * scale;
        bdy = 390 * scale;
        bnw = bdw = 930 * InputHandler.scale;
        bnh = 60 * InputHandler.scale;
        bdh = 60 * InputHandler.scale;

        setting = new SettingButton(w * 0.01f, h * 0.915f);
        tutorial = new TutorialButton(FileHandler.getUi().get("TUTORIAL_B"), w * 0.05f, h * 0.915f);

        gold = new TempUI(FileHandler.getUi().get("GOLD_PANEL"));
        gold.setPosition(974 * scale, 49 * scale);

        fy = 122 * scale;

        for (int i = 0; i < 4; i++) {
            PlayerIcon c = new PlayerIcon(i);
            c.setPosition((68 + 212 * i) * scale, cp.bg.y + cp.bg.sHeight / 2 - c.sHeight / 2);
            pIcons[i] = c;
        }
        aSkill = new ItemPanel();
        aSkill.adv = true;
        aSkill.setPosition(993 * scale, 280 * scale);
        aSkill.item = AbstractLabyrinth.advisor;
        map = new MapButton();
        map.setPosition(1269 * scale, 217 * scale);
        playerInfo = new PlayerInfoButton();
        playerInfo.setPosition(1269 * scale, 364 * scale);
    }

    @Override
    public void updateButton() {
        if (AbstractLabyrinth.cPanel.battlePanel.selected == null) {
            type = InfoType.COLOR;
            skill = null;
            name = "";
            desc = "";
            show = false;
        } else {
            type = SKILL;
            skill = AbstractLabyrinth.cPanel.battlePanel.selected;
            name = skill.name;
            desc = skill.desc;
            show = true;
        }
        status = null;
        if (renderIcon) {
            pIcons[0].update();
            if(AbstractLabyrinth.mode != AbstractLabyrinth.Mode.SOLO) {
                for (int i = 1; i < 4; i++) {
                    pIcons[i].update();
                }
            }
        }
        aSkill.item = AbstractLabyrinth.advisor;
        aSkill.update();
        map.update();
        playerInfo.update();
        tutorial.update();
        setting.update();
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            renderLineBotLeft(sb, fontName, name, nx, ny, nw, nh);
            if (type == InfoType.SKILL) {
                renderCardLeft(sb, skill, fontDesc, desc, dx, dy, dw, dh);
            } else {
                renderColorLeft(sb, fontDesc, desc, dx, dy, dw);
            }
            if (renderIcon) {
                pIcons[0].render(sb);
                if(AbstractLabyrinth.mode != AbstractLabyrinth.Mode.SOLO) {
                    for (int i = 1; i < 4; i++) {
                        pIcons[i].render(sb);
                    }
                }
            }
            gold.render(sb);
            FontHandler.renderColorCenter(
                    sb, GOLD, "&y<" + AbstractLabyrinth.gold + "G>", 1166 * scale, fy, 200 * scale);
            aSkill.render(sb);
            map.render(sb);
            playerInfo.render(sb);
            if(Labyrintale.getCurScreen() == Labyrintale.battleScreen) {
                tutorial.setType(TutorialScreen.TutorialType.BATTLE);
            } else if(Labyrintale.getCurScreen() == Labyrintale.wayScreen) {
                tutorial.setType(TutorialScreen.TutorialType.WAY);
            } else if(Labyrintale.getCurScreen() instanceof RewardScreen) {
                tutorial.setType(TutorialScreen.TutorialType.REWARD);
            } else tutorial.type = null;
            tutorial.render(sb);
            setting.render(sb);
        }
    }

    public void setInfo(AbstractSkill s) {
        if (s != null) {
            name = s.name;
            desc = s.desc;
            type = InfoType.SKILL;
            skill = s;
            show = true;
        }
    }

    public void setInfo(AbstractStatus s) {
        if (s != null) {
            name = s.name;
            desc = s.getDesc();
            type = InfoType.STATUS;
            status = s;
            show = true;
        }
    }

    public void setInfo(AbstractItem s) {
        if (s != null) {
            name = s.name;
            desc = s.desc;
            type = ITEM;
            item = s;
            show = true;
        }
    }

    public void setInfo(AbstractEntity p) {
        if (p != null) {
            name = p.name;
            desc = p.desc;
            type = PLAYER;
            player = p;
            show = true;
        }
    }

    public void setInfo(String name, String desc) {
        this.name = name;
        this.desc = desc;
        type = InfoType.COLOR;
    }

    public enum InfoType {
        SKILL,
        STATUS,
        ITEM,
        COLOR,
        PLAYER
    }
}
