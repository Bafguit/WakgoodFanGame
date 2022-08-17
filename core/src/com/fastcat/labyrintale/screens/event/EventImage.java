package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class EventImage extends AbstractUI {

    public AbstractEvent event;
    public FontHandler.FontData fontName = EVENT_TITLE;
    public FontHandler.FontData fontDesc = EVENT_DESC;
    public float nx, ny, nw, nh, dx, dy, dw, dh;

    public EventImage(AbstractEvent e) {
        super(FileHandler.getUi().get("EVENT"));
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        setPosition(w * 0.5f, h * 0.48f);
        event = e;
        overable = false;
        nx = dx = w * 0.5f;
        ny = h * 0.75f;
        dy = h * 0.75f - 50 * scale;
        nw = dw = 1200 * InputHandler.scale;
        nh = 50 * InputHandler.scale;
        dh = 360 * InputHandler.scale;
    }

    @Override
    protected void updateButton() {

    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (event != null) {
                //sb.setColor(Color.WHITE);
                sb.draw(event.img, x, y, sWidth, sHeight);
                renderLineLeft(sb, fontName, event.name, nx, ny, nw, nh);
                renderColorLeft(sb, fontDesc, event.desc, dx, dy, dw);
            }
            //sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
