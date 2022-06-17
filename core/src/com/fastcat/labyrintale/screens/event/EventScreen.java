package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class EventScreen extends AbstractScreen {

    public EventImage eventImage;
    public EventChoiceButton[] ecb;
    public AbstractEvent event;
    public int size;

    public EventScreen(AbstractEvent event) {
        this.event = event;
        eventImage = new EventImage(this.event);
        cType = ControlPanel.ControlType.BASIC;
        setPage(event.page);
    }

    public void setPage(int page) {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        size = event.choices[page].size;
        ecb = new EventChoiceButton[size];
        for(int i = 0; i < size; i++) {
            EventChoiceButton t = new EventChoiceButton(this.event.choices[page].get(i));
            t.setPosition(w * 0.4f, h * 0.5f + t.sHeight * 1.2f * (size - 1 - i));
            ecb[i] = t;
        }
    }

    @Override
    public void update() {
        for(EventChoiceButton b : ecb) {
            b.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        eventImage.render(sb);
        for(EventChoiceButton t : ecb) {
            t.render(sb);
        }
    }

    public void refresh() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
