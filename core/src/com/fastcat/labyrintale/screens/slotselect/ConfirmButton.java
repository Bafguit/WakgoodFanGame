package com.fastcat.labyrintale.screens.slotselect;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class ConfirmButton extends AbstractUI {

    private final ConfirmSlotScreen sc;

    public ConfirmButton(ConfirmSlotScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, 576 * InputHandler.scale);
        fontData = BUTTON;
        text = "결정";
        this.sc = sc;
    }

    @Override
    protected void onClick() {
        sc.slotSelected(sc.player, sc.index);
        Labyrintale.removeTempScreen(sc);
    }
}
