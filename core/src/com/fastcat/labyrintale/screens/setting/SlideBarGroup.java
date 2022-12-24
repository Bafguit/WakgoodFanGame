package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.FontHandler.renderLineRight;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.slidebar.SlideBar;

public class SlideBarGroup {

    public final SlideBar.SlideLine line;
    public final SlideBar.SlideButton button;
    public SlideBar slideBar;

    public SlideBarGroup(int start, float x, float y) {
        slideBar = new SlideBar(x, y, start, this);
        line = new SlideBar.SlideLine(slideBar);
        line.setPosition(slideBar.x, slideBar.y);
        button = new SlideBar.SlideButton();
        button.min = slideBar.x - button.sWidth / 2;
        button.max = slideBar.x + slideBar.sWidth - button.sWidth / 2;
        button.setPosition(
                slideBar.x + ((slideBar.sWidth) * ((float) slideBar.pos / slideBar.abs) - button.sWidth / 2), y - button.sHeight / 2);
    }

    public void update() {
        slideBar.update();
        button.update();
        slideBar.pos = MathUtils.floor((button.x + button.sWidth / 2 - slideBar.x) / (button.max + button.sWidth / 2 - slideBar.x) * slideBar.abs);
        line.update();
    }

    public void render(SpriteBatch sb) {
        slideBar.render(sb);
        slideBar.render(sb);
        line.render(sb);
        button.render(sb);
    }

    public void setParent(AbstractUI ui) {
        slideBar.setParent(ui);
        line.setParent(ui);
        button.setParent(ui);
    }

    public int getValue() {
        return slideBar.pos;
    }
}
