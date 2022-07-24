package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.slidebar.SlideBar;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.FontHandler.renderLineRight;

public class SlideBarGroup {

    public SlideBar slideBar;
    public Title title;

    public SlideBarGroup(String text, int start, float x, float y) {
        title = new Title(text, x, y);
        slideBar = new SlideBar(x + title.sWidth, y, start);
        title.slideBar = slideBar;
    }

    public void update() {
        slideBar.update();
        title.update();
    }

    public void render(SpriteBatch sb) {
        slideBar.render(sb);
        title.render(sb);
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
        public void render(SpriteBatch sb) {
            sb.setColor(Color.WHITE);
            renderKeywordCenter(sb, fontData, title, x, y + sHeight / 2, sWidth, sHeight);
            renderLineRight(sb, fontData, text, x + sWidth * 1.9f, y + sHeight / 2, sWidth * 0.5f, sHeight);
        }
    }
}
