package com.fastcat.labyrintale.screens.advisorselect;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class NextButton extends AbstractUI {

    private final AdvisorSelectScreen sc;

    public NextButton(AdvisorSelectScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, 1145 * InputHandler.scale);
        fontData = BUTTON;
        text = "결정";
        this.sc = sc;
    }

    @Override
    protected void onClick() {
        if (sc.selected != null) {
            if (AbstractLabyrinth.advisor != null) AbstractLabyrinth.advisor.onRemove();
            AbstractLabyrinth.advisor = sc.selected.advisor;
            AbstractLabyrinth.advisor.onGain();
            Labyrintale.removeTempScreen(sc);
        }
    }
}
