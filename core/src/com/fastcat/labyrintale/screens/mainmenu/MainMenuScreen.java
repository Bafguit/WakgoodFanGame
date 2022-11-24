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

  private final LogoText logoText;
  private final GameStartButton gameStartButton;
  private final LoadButton loadButton;
  private final RunsButton runsButton;
  private final OptionButton optionButton;
  private final ExitButton exitButton;
  private final GifBg back;

  public MainMenuScreen() {
    setBg(FileHandler.getBg().get("BG_MAIN"));
    logoText = new LogoText();
    gameStartButton = new GameStartButton();
    loadButton = new LoadButton();
    runsButton = new RunsButton();
    optionButton = new OptionButton();
    exitButton = new ExitButton();
    back = new GifBg("MAIN_MENU");

    cType = ControlPanel.ControlType.HIDE;
  }

  @Override
  public void update() {
    logoText.update();
    gameStartButton.update();
    loadButton.update();
    runsButton.update();
    optionButton.update();
    exitButton.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    back.render(sb);
    logoText.render(sb);
    gameStartButton.render(sb);
    loadButton.render(sb);
    runsButton.render(sb);
    optionButton.render(sb);
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
    logoText.onHide();
    gameStartButton.onHide();
    loadButton.onHide();
    runsButton.onHide();
    optionButton.onHide();
    exitButton.onHide();
    SaveHandler.refresh();
    RunHandler.load();
    if(Labyrintale.labyrinth != null) {
      SoundHandler.fadeOutAll();
      AbstractLabyrinth.reset();
      Labyrintale.labyrinth = null;
      SoundHandler.addMusic("LOBBY", true, true);
    } else if(playMusic) {
      playMusic = false;
      SoundHandler.addMusic("LOBBY", true, true);
    }
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
