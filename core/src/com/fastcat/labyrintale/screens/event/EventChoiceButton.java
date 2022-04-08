package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.FileHandler.NEXT;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class EventChoiceButton extends AbstractUI {

    public AbstractEvent.EventChoice choice;
    private boolean can;

    public EventChoiceButton(AbstractEvent.EventChoice choice) {
        super(MENU_SELECT);
        fontData = EVENT_DESC;
        showImg = false;
        this.choice = choice;
        can = this.choice.available();
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!can) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);

            if(fontData != null) {
                renderLineLeft(sb, fontData, choice.text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void updateButton() {
        can = choice.available();
        if(!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        if(can) choice.select();
    }
}
