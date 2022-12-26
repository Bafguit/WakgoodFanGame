package com.fastcat.labyrintale.screens.result;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class MoreResultButton extends AbstractUI {

    private final ResultScreen sc;

    public MoreResultButton(ResultScreen sc) {
        super(FileHandler.getUi().get("MORE_RESULT"));
        setPosition(1030 * InputHandler.scale, 142 * InputHandler.scale);
        isPixmap = true;
        this.sc = sc;
    }

    @Override
    protected void onClick() {
        Labyrintale.game.setScreen(sc.moreScreen);
    }
}
