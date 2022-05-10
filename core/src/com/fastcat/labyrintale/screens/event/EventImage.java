package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.EndPlayerTurnAction;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.interfaces.PrintTextEffect;
import com.fastcat.labyrintale.uis.control.InfoPanel;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.handlers.ActionHandler.bot;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class EventImage extends AbstractUI {

    public AbstractEvent event;
    public FontHandler.FontData fontName = INFO_NAME;
    public FontHandler.FontData fontDesc = INFO_HP;
    public float nx, ny, nw, nh, dx, dy, dw, dh;

    public EventImage(AbstractEvent e) {
        super(FileHandler.ui.get("WAY_SELECT"));
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        setPosition(w * 0.25f - sWidth / 2, h * 0.7f - sHeight / 2);
        event = e;
        overable = false;
        nx = dx = w * 0.4f;
        ny = h * 0.9f;
        dy = h * 0.9f - 50 * scale;
        nw = dw = 1000 * InputHandler.scale;
        nh = 60 * InputHandler.scale;
        dh = 280 * InputHandler.scale;
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(event != null) {
                sb.draw(event.img, x, y, sWidth, sHeight);
                renderLineLeft(sb, fontName, event.name, nx, ny, nw, nh);
                renderColorLeft(sb, fontDesc, event.desc, dx, dy, dw);
            }
            //sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }
}
