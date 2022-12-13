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

    public SlideBar slideBar;
    public final SlideBar.SlideLine line;
    public final SlideBar.SlideSideL sideL;
    public final SlideBar.SlideSideR sideR;
    public final SlideBar.SlideButton button;
    public Title title;

    public SlideBarGroup(String text, int start, float x, float y) {
        title = new Title(text, x, y);
        slideBar = new SlideBar(x + title.sWidth, y, start, this);
        title.slideBar = slideBar;
        sideL = new SlideBar.SlideSideL();
        sideL.setPosition(slideBar.x, slideBar.y);
        line = new SlideBar.SlideLine(slideBar.width);
        line.setPosition(slideBar.x + sideL.sWidth, slideBar.y);
        sideR = new SlideBar.SlideSideR();
        sideR.setPosition(slideBar.x + sideL.sWidth + line.sWidth, slideBar.y);
        button = new SlideBar.SlideButton();
        button.min = slideBar.x;
        button.max = slideBar.x + slideBar.sWidth - button.sWidth;
        button.setPosition(
                slideBar.x + ((slideBar.sWidth - button.sWidth) * ((float) slideBar.pos / slideBar.abs)), slideBar.y);
    }

    public void update() {
        slideBar.update();
        button.update();
        slideBar.pos = MathUtils.floor((button.x - slideBar.x) / (button.max - slideBar.x) * slideBar.abs);
        sideL.update();
        line.update();
        sideR.update();
        title.update();
    }

    public void render(SpriteBatch sb) {
        slideBar.render(sb);
        sideL.render(sb);
        line.render(sb);
        sideR.render(sb);
        button.render(sb);
        title.render(sb);
    }

    public void setParent(AbstractUI ui) {
        slideBar.setParent(ui);
        sideL.setParent(ui);
        line.setParent(ui);
        sideR.setParent(ui);
        button.setParent(ui);
        title.setParent(ui);
    }

    public void setText() {
        this.title.text = slideBar.pos + "%";
    }

    public int getValue() {
        return slideBar.pos;
    }

    private static class Title extends AbstractUI {
        public String title;
        public SlideBar slideBar;

        public Title(String title, float x, float y) {
            super(FileHandler.getUi().get("SLIDE_A"), x, y);
            this.title = title;
            fontData = FontHandler.SETTING;
            overable = false;
        }

        @Override
        protected void updateButton() {
            text = slideBar.pos + "%";
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            sb.setColor(Color.WHITE);
            renderKeywordCenter(sb, fontData, title, x, y + sHeight / 2, sWidth, sHeight);
            renderLineRight(sb, fontData, text, x + sWidth * 1.9f, y + sHeight / 2, sWidth * 0.5f, sHeight);
        }
    }
}
