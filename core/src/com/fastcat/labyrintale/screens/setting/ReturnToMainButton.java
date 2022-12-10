package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class ReturnToMainButton extends AbstractUI {

    public ReturnToMainButton(SettingScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.21f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f);
        fontData = BUTTON;
        text = "메인 메뉴";
        screen = sc;
        isPixmap = true;
    }

    @Override
    protected void onClick() {
        SettingHandler.save();
        SoundHandler.fadeOutAll();
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
    }
}
