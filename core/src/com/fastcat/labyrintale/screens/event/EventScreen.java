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
        cType = ControlPanel.ControlType.BASIC;
        this.event = event;
        this.event.generateChoices();
        eventImage = new EventImage(this.event);
        panel = new AbstractUI.TempUI(FileHandler.getUi().get("EVENT_PANEL"));
        panel.setPosition(0, 0);
        setPage(event.page);
        setBg(AbstractLabyrinth.curBg);
        cx = 1118 * InputHandler.scale;
        cy = 733 * InputHandler.scale;
        ch = 64 * InputHandler.scale;
    }

    public void setPage(int page) {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
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
                    1085 * InputHandler.scale, 678 * InputHandler.scale + 67 * InputHandler.scale * (size - 1 - i));
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

    public void refresh() {}

    @Override
    public void show() {
        SoundHandler.addWay().stop = false;
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
