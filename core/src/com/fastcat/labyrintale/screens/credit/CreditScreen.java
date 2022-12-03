package com.fastcat.labyrintale.screens.credit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.charselect.BackButton;
import com.fastcat.labyrintale.strings.CreditString;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.GifBg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.Labyrintale.mainMenuScreen;

public class CreditScreen extends AbstractScreen {

    private SoundHandler.MusicData music;
    public boolean clicked = false;
    public CreditBackButton backButton;
    public AbstractUI.TempUI logo;
    private final GifBg back;
    private final BgImg bg;

    public CreditScreen() {
        setBg(FileHandler.getBg().get("BG_MAIN"));
        backButton = new CreditBackButton();
        backButton.disable();
        logo = new AbstractUI.TempUI(FileHandler.getUi().get("CREDIT"));
        logo.setPosition(0, -logo.sHeight);
        back = new GifBg("MAIN_MENU");
        bg = new BgImg();

        cType = ControlPanel.ControlType.HIDE;
    }

    @Override
    public void update() {
        if(InputHandler.isLeftClick) {
            if(!clicked) {
                clicked = true;
                backButton.enable();
            }
        } else if(InputHandler.cancel) {
            SoundHandler.fadeOutAll();
            mainMenuScreen.playMusic = true;
            Labyrintale.fadeOutAndChangeScreen(mainMenuScreen, 1.5f);
        }
        if(logo.y < 0) {
            logo.y += Labyrintale.tick * 138 * InputHandler.scale;
            if(logo.y > 0) logo.y = 0;
        } else if(music != null && !music.music.isPlaying()) {
            clicked = true;
            backButton.enable();
        }
        backButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        mainMenuScreen.back.render(sb);
        bg.render(sb);
        logo.render(sb);
        backButton.render(sb);
    }

    @Override
    public void show() {
        music = SoundHandler.addMusic("CREDIT", false, true);
    }
}
