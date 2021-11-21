package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Texture;

public abstract class AbstractImage extends AbstractUI {

    public AbstractImage(Texture texture) {
        super(texture);
    }

    public AbstractImage(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }

    public AbstractImage(String imgPath, float x, float y, float width, float height) {
        super(imgPath, x, y, width, height);
    }
}
