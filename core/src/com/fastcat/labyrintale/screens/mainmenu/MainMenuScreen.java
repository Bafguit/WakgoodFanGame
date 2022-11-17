package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.CheckBox;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import com.fastcat.labyrintale.uis.slidebar.SlideBar;

public class MainMenuScreen extends AbstractScreen {

  private final LogoText logoText;
  private final GameStartButton gameStartButton;
  private final TutorialButton tutorialButton;
  private final OptionButton optionButton;
  private final ExitButton exitButton;

  public MainMenuScreen() {
    setBg(FileHandler.getBg().get("BG_MAIN"));
    logoText = new LogoText();
    gameStartButton = new GameStartButton();
    tutorialButton = new TutorialButton();
    optionButton = new OptionButton();
    exitButton = new ExitButton();

    cType = ControlPanel.ControlType.HIDE;
  }

  @Override
  public void update() {
    logoText.update();
    gameStartButton.update();
    tutorialButton.update();
    optionButton.update();
    exitButton.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    logoText.render(sb);
    gameStartButton.render(sb);
    tutorialButton.render(sb);
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
    tutorialButton.onHide();
    optionButton.onHide();
    exitButton.onHide();
    SaveHandler.refresh();
    if(Labyrintale.labyrinth != null) {
      SoundHandler.fadeOutAll();
      AbstractLabyrinth.reset();
      Labyrintale.labyrinth = null;
    }
    if (playMusic) {
      SoundHandler.addMusic("LOBBY", true, true);
      playMusic = false;
    }
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
