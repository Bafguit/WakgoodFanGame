package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.SETTING;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class ResetTutorialButton extends AbstractUI {

    public ResetTutorialButton(AbstractScreen screen) {
        super(FileHandler.getUi().get("MENU_SELECT"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, 1152 * InputHandler.scale);
        fontData = SETTING;
        text = "튜토리얼 초기화";
        showImg = false;
        this.screen = screen;
    }

    @Override
    protected void updateButton() {
        if (!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        SettingHandler.setting.rewardTutorial = true;
        SettingHandler.setting.wayTutorial = true;
        SettingHandler.setting.charTutorial = true;
        SettingHandler.setting.battleTutorial = true;
        SettingHandler.save();
    }
}
