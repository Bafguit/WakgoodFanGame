package com.fastcat.labyrintale;

import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.SettingHandler.alwaysValue;

public class TestButton extends AbstractUI {

    private static final String IMG = "badlogic.jpg";

    public TestButton() {
        super(IMG, 100, 100);
        trackable = true;
        setText("");
    }

    @Override
    protected void updateButton() {
        this.text = "X:" + this.x + "\nY:" + this.y;
    }

    @Override
    protected void onClick() {
        if(alwaysValue) {
            alwaysValue = false;
        } else {
            alwaysValue = true;
        }
    }

    @Override
    protected void onClicking() {
        trackCursor(false);
    }
}
