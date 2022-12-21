package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.CheckBox;

public class CheckBoxGroup {

    public CheckBox checkBox;

    public CheckBoxGroup(boolean checked, float x, float y) {
        checkBox = new CheckBox(checked);
        checkBox.setPosition(x - checkBox.sWidth * 0.5f, y - checkBox.sHeight * 0.5f);
    }

    public void update() {
        checkBox.update();
    }

    public void render(SpriteBatch sb) {
        checkBox.render(sb);
    }

    public void setParent(AbstractUI ui) {
        checkBox.setParent(ui);
    }

    public boolean getValue() {
        return checkBox.checked;
    }
}
