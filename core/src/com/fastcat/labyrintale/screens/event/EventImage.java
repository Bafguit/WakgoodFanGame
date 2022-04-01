package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.EndPlayerTurnAction;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.handlers.ActionHandler.bot;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.BORDER_B;
import static com.fastcat.labyrintale.handlers.FileHandler.BORDER_M;

public class EventImage extends AbstractUI {

    public AbstractEvent event;

    public EventImage(AbstractEvent e) {
        super(BORDER_B);
        setPosition(Gdx.graphics.getWidth() * 0.25f - sWidth / 2, Gdx.graphics.getHeight() * 0.7f - sHeight / 2);
        event = e;
        overable = false;
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(event != null) sb.draw(event.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }
}
