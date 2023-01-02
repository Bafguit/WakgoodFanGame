package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractAnimation {
        public abstract void render(SpriteBatch sb, float x, float y);
        public abstract void setAndIdle(String key);
        public abstract void resetAnimation();
        public abstract void setSkin(String key);
        public abstract void setDie();
}