package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.PlayerIcon;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;
import static com.fastcat.labyrintale.uis.control.InfoPanel.InfoType.*;

public class InfoPanel extends AbstractUI {

    public Sprite border = FileHandler.ui.get("BORDER_B");
    public String name = "";
    public String desc = "";
    public FontHandler.FontData fontName = CARD_BIG_NAME;
    public FontHandler.FontData fontDesc = CARD_BIG_DESC;
    public PlayerIcon[] pIcons = new PlayerIcon[4];
    public InfoType type = InfoType.COLOR;
    public AbstractSkill skill;
    public AbstractStatus status;
    public AbstractItem item;
    public AbstractPlayer player;
    public boolean show;
    public boolean renderIcon = true;
    public float nx, ny, nw, nh, dx, dy, dw, dh;
    public float bnx, bny, bnw, bnh, bdx, bdy, bdw, bdh;

    private AbstractSkill.SkillTarget target;

    public InfoPanel() {
        super(FileHandler.ui.get("BORDER_B"));
        clickable = false;
        overable = false;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        setPosition(w * 0.5f, h * 0.26f - sHeight / 2);
        nx = dx = w * 0.69f;
        ny = h * 0.41f;
        dy = h * 0.4f - 45 * scale;
        nw = dw = 650 * InputHandler.scale;
        nh = 60 * InputHandler.scale;
        dh = 280 * InputHandler.scale;

        bnx = 970 * scale;
        bny = 445 * scale;
        bdx = 1000 * scale;
        bdy = 390 * scale;
        bnw = bdw = 1000 * InputHandler.scale;
        bnh = 60 * InputHandler.scale;
        bdh = 60 * InputHandler.scale;

        for(int i = 0; i < 4; i++) {
            PlayerIcon c = new PlayerIcon(AbstractLabyrinth.players[i]);
            c.setPosition(w * (0.17f + 0.08f * i) - c.sWidth / 2, h * 0.275f);
            pIcons[i] = c;
        }

    }

    @Override
    public void updateButton() {
        type = InfoType.COLOR;
        skill = null;
        status = null;
        target = null;
        name = "";
        desc = "";
        show = false;
        if(renderIcon) {
            for (int i = 0; i < 4; i++) {
                pIcons[i].update();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            if(img != null) {
                if(renderIcon) {
                    renderLineBotLeft(sb, fontName, name, nx, ny, nw, nh);
                    if (type == InfoType.SKILL) {
                        renderCardLeft(sb, skill, fontDesc, desc, dx, dy, dw, dh);
                    } else {
                        renderColorLeft(sb, fontDesc, desc, dx, dy, dw);
                    }
                } else {
                    renderLineBotLeft(sb, fontName, name, bnx, bny, bnw, bnh);
                    if (type == InfoType.SKILL) {
                        renderCardLeft(sb, skill, fontDesc, desc, bdx, bdy, bdw, bdh);
                    } else {
                        renderColorLeft(sb, fontDesc, desc, bdx, bdy, bdw);
                    }
                }
            }
            if(renderIcon) {
                if (target != null)
                    renderCenter(sb, fontDesc, AbstractSkill.getTargetString(target), x, y - sHeight * 0.04f, sWidth, sHeight);
            }
        }

        if(renderIcon) {
            for (int i = 0; i < 4; i++) {
                pIcons[i].render(sb);
            }
        }
    }

    public void setInfo(AbstractSkill s) {
        if(s != null) {
            name = s.name;
            desc = s.desc;
            type = InfoType.SKILL;
            skill = s;
            target = skill.target;
            show = true;
        }
    }

    public void setInfo(AbstractStatus s) {
        if(s != null) {
            name = s.name;
            desc = s.getDesc();
            type = STATUS;
            status = s;
            target = status.target;
            show = true;
        }
    }

    public void setInfo(AbstractItem s) {
        if(s != null) {
            name = s.name;
            desc = s.desc;
            type = ITEM;
            item = s;
            target = null;
            show = true;
        }
    }

    public void setInfo(AbstractPlayer p) {
        if(p != null) {
            name = p.name;
            desc = p.desc;
            type = PLAYER;
            player = p;
            target = null;
            show = true;
        }
    }

    public void setInfo(String name, String desc) {
        this.name = name;
        this.desc = desc;
        type = InfoType.COLOR;
    }

    public enum InfoType {
        SKILL, STATUS, ITEM, COLOR, PLAYER
    }
}
