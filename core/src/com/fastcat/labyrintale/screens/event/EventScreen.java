package com.fastcat.labyrintale.screens.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class EventScreen extends AbstractScreen {

    private final AbstractUI.TempUI panel;
    public EventImage eventImage;
    public EventChoiceButton[] ecb;
    public AbstractEvent event;
    public int size;
    public float cx, cy, ch;

    public EventScreen(AbstractEvent event) {
        float w = Gdx.graphics.getWidth();
        cType = ControlPanel.ControlType.BASIC;
        this.event = event;
        this.event.generateChoices();
        eventImage = new EventImage(this.event);
        panel = new AbstractUI.TempUI(FileHandler.getUi().get("EVENT_PANEL"));
        panel.setPosition(0, 0);
        setPage(event.page);
        setBg(AbstractLabyrinth.curBg);
        cx = w * 0.437f * InputHandler.scale;
        cy = 713 * InputHandler.scale;
        ch = 64 * InputHandler.scale;
    }

    public void setPage(int page) {
        float w = Gdx.graphics.getWidth();
        size = event.choices[page].size;
        if (ecb != null) {
            for (EventChoiceButton bb : ecb) {
                bb.dispose();
            }
        }
        ecb = new EventChoiceButton[size];
        for (int i = 0; i < size; i++) {
            EventChoiceButton t = new EventChoiceButton(this.event.choices[page].get(i));
            t.setPosition(
                    w * 0.424f, (678 + 67 * (size - 1 - i)) * InputHandler.scale);
            ecb[i] = t;
        }
    }

    @Override
    public void update() {
        for (EventChoiceButton b : ecb) {
            b.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        panel.render(sb);
        eventImage.render(sb);
        for (EventChoiceButton t : ecb) {
            t.render(sb);
        }
    }

    @Override
    public void show() {
        SoundHandler.addWay().stop = false;
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
