package com.fastcat.labyrintale.uis.slidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import org.graalvm.compiler.loop.MathUtil;

import static com.fastcat.labyrintale.handlers.InputHandler.*;

public class SlideButton extends AbstractUI {

    protected boolean overTrack = false;
    protected float min, max;

    public SlideButton() {
        super(FileHandler.ui.get("SLIDE_B"));
        clickable = false;
    }

    @Override
    protected void updateButton() {
        if(overTrack) setPosition(MathUtils.clamp(mx - sWidth / 2, min, max), y);
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
