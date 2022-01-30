package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.handlers.FileHandler.BORDER_B;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class InfoPanel extends AbstractUI {

    public Sprite border = BORDER_B;
    public String name = "";
    public String desc = "";
    public FontHandler.FontData fontName = CARD_BIG_NAME;
    public FontHandler.FontData fontDesc = CARD_BIG_DESC;
    public InfoType type = InfoType.COLOR;
    public AbstractSkill skill;
    public float nx, ny, nw, nh, dx, dy, dw, dh;

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
        name = "";
        desc = "";
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            if(img != null) sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            renderLineLeft(sb, fontName, name, nx, ny, nw, nh);
            if(type == InfoType.SKILL && skill != null) renderCardLeft(sb, skill, fontDesc, desc, dx, dy, dw, dh);
            else renderColorLeft(sb, fontDesc, desc, dx, dy, dw);
        }
    }

    public void setInfo(AbstractSkill s) {
        setInfo(s.img, s.name, s.desc);
        type = InfoType.SKILL;
        skill = s;
    }

    public void setInfo(Sprite img, String name, String desc) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.type = InfoType.COLOR;
    }

    public enum InfoType {
        SKILL, COLOR
    }
}
