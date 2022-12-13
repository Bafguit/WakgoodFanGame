package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.RunHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.GifBg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class MainMenuScreen extends AbstractScreen {

    private final LogoText logo;
    private final GameStartButton gameStartButton;
    private final LoadButton loadButton;
    private final LibButton runsButton;
    private final OptionButton optionButton;
    private final CreditButton creditButton;
    private final ExitButton exitButton;
    public final GifBg back;

    public MainMenuScreen() {
        setBg(FileHandler.getBg().get("BG_MAIN"));
        logo = new LogoText();
        gameStartButton = new GameStartButton();
        float lx = logo.x + (logo.sWidth - gameStartButton.sWidth) / 2;
        gameStartButton.setX(lx);
        loadButton = new LoadButton();
        loadButton.setX(lx);
        runsButton = new LibButton();
        runsButton.setX(lx);
        optionButton = new OptionButton();
        optionButton.setX(lx);
        creditButton = new CreditButton();
        creditButton.setX(lx);
        exitButton = new ExitButton();
        exitButton.setX(lx);
        back = new GifBg("MAIN_MENU");

        cType = ControlPanel.ControlType.HIDE;
    }

    @Override
    public void update() {
        logo.update();
        gameStartButton.update();
        loadButton.update();
        runsButton.update();
        optionButton.update();
        creditButton.update();
        exitButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        back.render(sb);
        logo.render(sb);
        gameStartButton.render(sb);
        loadButton.render(sb);
        runsButton.render(sb);
        optionButton.render(sb);
        creditButton.render(sb);
        exitButton.render(sb);
    }

    @Override
    public void onCreate() {
        playMusic = true;
    }

    @Override
    public void show() {
        if (Labyrintale.charSelectScreen != null) {
            Labyrintale.charSelectScreen.selected = null;
            for (int i = 0; i < 4; i++) {
                Labyrintale.charSelectScreen.chars[i].removeChar();
            }
        }
        Labyrintale.closeTutorial();
        gameStartButton.over = false;
        gameStartButton.show();
        loadButton.over = false;
        loadButton.show();
        runsButton.over = false;
        runsButton.show();
        optionButton.over = false;
        optionButton.show();
        creditButton.over = false;
        creditButton.show();
        exitButton.over = false;
        exitButton.show();
        SaveHandler.refresh();
        RunHandler.load();
        if (Labyrintale.labyrinth != null) {
            SoundHandler.fadeOutAll();
            AbstractLabyrinth.reset();
            SoundHandler.reset();
            Labyrintale.labyrinth = null;
            SoundHandler.addMusic("LOBBY", true, true);
        } else if (playMusic) {
            playMusic = false;
            SoundHandler.addMusic("LOBBY", true, true);
        }
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
