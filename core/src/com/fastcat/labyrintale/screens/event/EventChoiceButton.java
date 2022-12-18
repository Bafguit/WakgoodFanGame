package com.fastcat.labyrintale.screens.event;

import static com.fastcat.labyrintale.handlers.FontHandler.FontData;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class EventChoiceButton extends AbstractUI {

    public AbstractEvent.EventChoice choice;

    public EventChoiceButton(AbstractEvent.EventChoice choice) {
        super(FileHandler.getUi().get("EVENT_CHOICE"));
        fontData = new FontData(MEDIUM, 26, new Color(0.85f, 0.8f, 0.745f, 1), false, false);
        this.choice = choice;
        clickable = choice.available();
    }

    @Override
    public Array<SubText> getSubText() {
        return choice.getSubText();
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!clickable) {
                sb.setColor(Color.DARK_GRAY);
                fontData.color = Color.DARK_GRAY;
            } else if (!over) {
                sb.setColor(Color.LIGHT_GRAY);
                fontData.color = Color.LIGHT_GRAY;
            } else {
                sb.setColor(Color.WHITE);
                fontData.color = Color.WHITE;
            }
            sb.draw(img, x, y, sWidth, sHeight);

            sb.setColor(Color.WHITE);
            renderLineLeft(
                    sb,
                    fontData,
                    choice.condition.condition() ? choice.text : choice.condition.cdText(),
                    x + 14 * InputHandler.scale,
                    y + sHeight * 0.5f,
                    sWidth,
                    sHeight);
        }
    }

    @Override
    protected void updateButton() {
        clickable = choice.available();
        if (!over && showImg) showImg = false;
    }

    @Override
    protected void onClick() {
        if (clickable) {
            choice.select();
            Labyrintale.eventScreen.event.onChoose();
        }
    }
}
