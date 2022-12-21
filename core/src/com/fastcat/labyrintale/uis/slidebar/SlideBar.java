package com.fastcat.labyrintale.uis.slidebar;

import static com.fastcat.labyrintale.handlers.InputHandler.mx;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.setting.SlideBarGroup;

public class SlideBar extends AbstractUI {

    public SlideBarGroup group;
    public int min, max, abs;
    public int pos; // 0 ~ 100;

    public SlideBar(float x, float y, int start, SlideBarGroup group) {
        this(x, y, 0, 100, start);
        this.group = group;
    }

    public SlideBar(float x, float y, int min, int max, int start) {
        super(FileHandler.getUi().get("SLIDE"));
        setPosition(x, y - sHeight / 2);
        this.min = min;
        this.max = max;
        abs = max - min;
        pos = start;
    }

    @Override
    protected void updateButton() {
        if (!clicking) group.button.overTrack = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        sb.draw(img, x, y, sWidth, sHeight);
    }

    @Override
    public void onClick() {
        group.button.overTrack = true;
    }

    public static class SlideButton extends AbstractUI {

        public float min, max;
        protected boolean overTrack = false;

        public SlideButton() {
            super(FileHandler.getUi().get("SLIDE_B"));
            clickable = false;
        }

        @Override
        protected void updateButton() {
            if (overTrack) setX(MathUtils.clamp(mx - sWidth / 2, min, max));
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                sb.setColor(Color.WHITE);
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }
    }

    public static class SlideLine extends AbstractUI {

        private final SlideBar bar;

        public SlideLine(SlideBar bar) {
            super(FileHandler.getUi().get("SLIDE_A"));
            this.bar = bar;
            clickable = false;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                sb.draw(img, x, y, 0, 0, sWidth, sHeight, (float) bar.pos / 100, 1, 0);
            }
        }
    }
}
