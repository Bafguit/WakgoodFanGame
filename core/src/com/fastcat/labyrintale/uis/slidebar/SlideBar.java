package com.fastcat.labyrintale.uis.slidebar;

import static com.fastcat.labyrintale.handlers.InputHandler.mx;

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
        super(FileHandler.getUi().get("SLIDE_A"), x, y);
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
    protected void renderUi(SpriteBatch sb) {}

    @Override
    public void onClick() {
        group.button.overTrack = true;
    }

    public static class SlideButton extends AbstractUI {

        protected boolean overTrack = false;
        public float min, max;

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
        public SlideLine(float width) {
            super(FileHandler.getUi().get("SLIDE_L"), -1000, -1000, width - 14, 40);
            clickable = false;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }
    }

    public static class SlideSideL extends AbstractUI {
        public SlideSideL() {
            super(FileHandler.getUi().get("SLIDE_SL"));
            clickable = false;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }
    }

    public static class SlideSideR extends AbstractUI {
        public SlideSideR() {
            super(FileHandler.getUi().get("SLIDE_SR"));
            clickable = false;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                sb.setColor(Color.WHITE);
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }
    }
}
