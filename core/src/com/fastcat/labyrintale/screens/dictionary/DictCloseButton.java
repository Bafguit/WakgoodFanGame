package com.fastcat.labyrintale.screens.dictionary;

import static com.fastcat.labyrintale.handlers.FontHandler.CLOSE;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class DictCloseButton extends AbstractUI {

    private final DictScreen sc;

    public DictCloseButton(DictScreen sc) {
        super(FileHandler.getUi().get("CLOSE_SET"));
        setPosition(Gdx.graphics.getWidth() * 0.85f - sWidth / 2, 1145 * InputHandler.scale - sHeight / 2);
        fontData = CLOSE;
        text = "×";
        this.sc = sc;
    }

    @Override
    protected void onClick() {
        sc.close();
    }
}
