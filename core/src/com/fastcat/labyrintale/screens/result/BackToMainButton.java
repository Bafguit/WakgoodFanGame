package com.fastcat.labyrintale.screens.result;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.credit.CreditScreen;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.result.moreinfo.MoreResultScreen;

public class BackToMainButton extends AbstractUI {

    private final MoreResultScreen sc;

    public BackToMainButton(MoreResultScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, 72 * InputHandler.scale);
        fontData = BUTTON;
        text = "메인 메뉴";
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
