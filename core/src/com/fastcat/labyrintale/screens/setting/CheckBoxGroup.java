package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.CheckBox;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class CheckBoxGroup {

    public CheckBox checkBox;
    public Title title;

    public CheckBoxGroup(String text, boolean checked, float x, float y) {
        title = new Title(text, x, y);
        checkBox = new CheckBox(checked);
        checkBox.setPosition(x + title.sWidth * 1.5f - checkBox.sWidth * 0.5f, y + title.sHeight * 0.5f - checkBox.sHeight * 0.5f);
    }

    public void update() {
        title.update();
        checkBox.update();
    }

    public void render(SpriteBatch sb) {
        title.render(sb);
        checkBox.render(sb);
    }

    public boolean getValue() {
        return checkBox.checked;
    }

    private static class Title extends AbstractUI {
        public String title;

        public Title(String title, float x, float y) {
            super(FileHandler.getUi().get("SLIDE_A"), x, y);
            this.title = title;
            fontData = FontHandler.SETTING;
            overable = false;
        }

        @Override
        public void render(SpriteBatch sb) {
            sb.setColor(Color.WHITE);
            renderKeywordCenter(sb, fontData, title, x, y + sHeight / 2, sWidth, sHeight);
        }
    }
}
