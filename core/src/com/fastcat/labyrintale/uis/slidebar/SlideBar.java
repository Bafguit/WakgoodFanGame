package com.fastcat.labyrintale.uis.slidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

public class SlideBar extends AbstractUI {

    private final SlideLine line;
    private final SlideSideL sideL;
    private final SlideSideR sideR;
    private final SlideButton button;
    public int pos; //0 ~ 100;

    public SlideBar(float x, float y, float width, int start) {
        super(FileHandler.ui.get("SLIDE_A"), x, y, width, 40);
        sideL = new SlideSideL();
        sideL.setPosition(x, y);
        line = new SlideLine(width);
        line.setPosition(x + sideL.sWidth, y);
        sideR = new SlideSideR();
        sideR.setPosition(x + sideL.sWidth + line.sWidth, y);
        pos = start;
        button = new SlideButton();
        button.min = x;
        button.max = x + sWidth - button.sWidth;
        button.setPosition(x + ((sWidth - button.sWidth) * (pos * 0.01f)), y);
    }

    @Override
    protected void updateButton() {
        button.overTrack = clicking;
        button.update();
        pos = MathUtils.floor((button.x - x) / (button.max - x) * 100);
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            sideL.render(sb);
            line.render(sb);
            sideR.render(sb);
            button.render(sb);

            renderLineLeft(sb, FontHandler.CARD_BIG_DESC, Integer.toString(pos), x, y + 100, sWidth, sHeight);
        }
    }
}
