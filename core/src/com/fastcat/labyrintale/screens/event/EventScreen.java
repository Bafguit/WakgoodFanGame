package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractScreen;

public class EventScreen extends AbstractScreen {

    public EventImage eventImage;
    public AbstractEvent event;

    public EventScreen(AbstractEvent event) {
        this.event = event;
        eventImage = new EventImage(this.event);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        eventImage.render(sb);
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
