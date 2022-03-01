package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.deckview.DeckSkillButton;

import java.util.Objects;

import static com.fastcat.labyrintale.handlers.FileHandler.BORDER_B;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;
import static com.fastcat.labyrintale.uis.control.InfoPanel.InfoType.STATUS;

public class InfoPanel extends AbstractUI {

    public Sprite border = BORDER_B;
    public String name = "";
    public String desc = "";
    public FontHandler.FontData fontName = CARD_BIG_NAME;
    public FontHandler.FontData fontDesc = CARD_BIG_DESC;
    public InfoType type = InfoType.COLOR;
    public AbstractSkill skill;
    public AbstractStatus status;
    public boolean show;
    public float nx, ny, nw, nh, dx, dy, dw, dh;

    private AbstractSkill.SkillTarget target;

    public InfoPanel() {
        super(BORDER_B);
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        setPosition(w * 0.55f, h * 0.2f - sHeight / 2);
        nx = dx = w * 0.69f;
        ny = h * 0.3f;
        dy = h * 0.3f - 45 * scale;
        nw = dw = 400 * InputHandler.scale;
        nh = 60 * InputHandler.scale;
        dh = 180 * InputHandler.scale;
    }

    @Override
    public void updateButton() {
        img = null;
        type = InfoType.COLOR;
        skill = null;
        status = null;
        target = null;
        name = "";
        desc = "";
        show = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            if(img != null) sb.draw(img, x, y, sWidth, sHeight);
            renderLineLeft(sb, fontName, name, nx, ny, nw, nh);
            if(type == InfoType.SKILL) {
                renderCardLeft(sb, skill, fontDesc, desc, dx, dy, dw, dh);
            }
            else {
                renderColorLeft(sb, fontDesc, desc, dx, dy, dw);
            }
            sb.draw(border, x, y, sWidth, sHeight);
            if(target != null) renderCenter(sb, fontDesc, AbstractSkill.getTargetString(target), x, y - sHeight * 0.1f, sWidth, sHeight);
        }
    }

    public void setInfo(AbstractSkill s) {
        if(s != null) {
            img = s.imgBig;
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
            img = s.imgBig;
            name = s.name;
            desc = s.getDesc();
            type = STATUS;
            status = s;
            target = status.target;
            show = true;
        }
    }

    public void setInfo(Sprite img, String name, String desc) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        type = InfoType.COLOR;
    }

    public enum InfoType {
        SKILL, STATUS, COLOR
    }
}