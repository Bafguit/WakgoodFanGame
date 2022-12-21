package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class SelectionGroup {

    public String[] itemText;
    public int[] item;
    public int[] item2;
    public Text text;
    public Right right;
    public Left left;
    public int index;
    public boolean can = true;

    public SelectionGroup(String[] itemText, float x, float y) {
        this.itemText = itemText;
        left = new Left(this);
        left.setPosition(x - left.sWidth, y - left.sHeight * 0.5f);
        text = new Text(this);
        text.setPosition(x, y - text.sHeight * 0.5f);
        right = new Right(this);
        right.setPosition(x + text.sWidth, left.y);
    }

    public void setParent(AbstractUI ui) {
        text.setParent(ui);
        right.setParent(ui);
        left.setParent(ui);
    }

    public void update() {
        text.update();
        right.update();
        left.update();
    }

    public void render(SpriteBatch sb) {
        if (can) sb.setColor(Color.WHITE);
        else sb.setColor(Color.DARK_GRAY);
        text.render(sb);
        right.render(sb);
        left.render(sb);
        sb.setColor(Color.WHITE);
    }

    public String getText() {
        return text.text;
    }

    public void setText() {
        this.text.text = itemText[index];
    }

    public int getItem() {
        return item[index];
    }

    public void modifyIndex(int i) {
        index = MathUtils.clamp(index + i, 0, itemText.length - 1);
        setText();
    }

    private static class Text extends AbstractUI {
        public SelectionGroup ui;

        public Text(SelectionGroup ui) {
            super(FileHandler.getUi().get("SLIDE"));
            fontData = FontHandler.SETTING;
            this.ui = ui;
            overable = false;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (!ui.can) sb.setColor(Color.DARK_GRAY);
            Color c = fontData.color;
            fontData.color = sb.getColor();
            renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            fontData.color = c;
        }
    }

    private static class Right extends AbstractUI {
        public SelectionGroup ui;

        public Right(SelectionGroup ui) {
            super(FileHandler.getUi().get("RIGHT"));
            this.ui = ui;
        }

        @Override
        protected void updateButton() {
            overable = ui.can && ui.index < ui.itemText.length - 1;
        }

        @Override
        public void onClick() {
            ui.modifyIndex(1);
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (!overable) sb.setColor(Color.DARK_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    private static class Left extends AbstractUI {
        public SelectionGroup ui;

        public Left(SelectionGroup ui) {
            super(FileHandler.getUi().get("LEFT"));
            this.ui = ui;
        }

        @Override
        protected void updateButton() {
            overable = ui.can && ui.index > 0;
        }

        @Override
        public void onClick() {
            ui.modifyIndex(-1);
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (!overable) sb.setColor(Color.DARK_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
