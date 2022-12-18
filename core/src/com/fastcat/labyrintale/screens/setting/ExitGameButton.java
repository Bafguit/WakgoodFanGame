package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class ExitGameButton extends AbstractUI {

    public ExitGameButton(SettingScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.67f - sWidth / 2, 288 * InputHandler.scale);
        fontData = BUTTON;
        text = "종료";
        screen = sc;
        isPixmap = true;
    }

    @Override
    protected void onClick() {
        SettingHandler.save();
        Gdx.app.exit();
    }
}
