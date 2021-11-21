package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Texture;

public abstract class AbstractButton extends AbstractUI {

    public AbstractButton(Texture texture) {
        super(texture);
    }

    public AbstractButton(String imgPath, float x, float y, float width, float height) {
        super(imgPath, x, y, width, height);
    }
}
