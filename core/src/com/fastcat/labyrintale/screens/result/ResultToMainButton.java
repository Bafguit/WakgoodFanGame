package com.fastcat.labyrintale.screens.result;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.credit.CreditScreen;
import com.fastcat.labyrintale.screens.dead.DeadScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

public class ResultToMainButton extends AbstractUI {

    private final ResultScreen sc;

    public ResultToMainButton(ResultScreen sc) {
        super(FileHandler.getUi().get("SETTING_MAIN"));
        setPosition(1305 * InputHandler.scale, 142 * InputHandler.scale);
        isPixmap = true;
        this.sc = sc;
    }

    @Override
    protected void onClick() {
        if(sc.dType == DeadScreen.ScreenType.WIN && SettingHandler.setting.forceCredit) {
            SettingHandler.setting.forceCredit = false;
            SettingHandler.save();
            SoundHandler.fadeOutAll();
            Labyrintale.fadeOutAndChangeScreen(new CreditScreen(), 1.5f);
        } else {
            SoundHandler.fadeOutAll();
            Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
        }
    }
}
