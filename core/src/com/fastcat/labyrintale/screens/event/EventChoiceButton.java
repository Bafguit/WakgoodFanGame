package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class EventChoiceButton extends AbstractUI {

    public AbstractEvent.EventChoice choice;

    public EventChoiceButton(AbstractEvent.EventChoice choice) {
        super(EVENT_CHOICE);
        fontData = new FontData(MEDIUM, 30, false, true);;
        this.choice = choice;
        clickable = choice.available();
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!clickable) {
                sb.setColor(Color.DARK_GRAY);
                fontData.color = Color.DARK_GRAY;
            }
            else if (!over) {
                sb.setColor(Color.LIGHT_GRAY);
                fontData.color = Color.LIGHT_GRAY;
            }
            else {
                sb.setColor(Color.WHITE);
                fontData.color = Color.WHITE;
            }
            sb.draw(img, x, y, sWidth, sHeight);

            if(fontData != null) {
                renderLineLeft(sb, fontData, choice.text, x + 10, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void updateButton() {
        clickable = choice.available();
        if(!over && showImg) showImg = false;
    }

    @Override
    protected void onClick() {
        if(clickable) choice.select();
    }
}
