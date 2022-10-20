package com.fastcat.labyrintale.screens.mainmenu;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class GameStartButton extends AbstractUI {

  public GameStartButton() {
    super(FileHandler.getUi().get("MENU_SELECT"));
    setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.5f);
    fontData = MAIN_MENU;
    text = "게임 시작";
    showImg = false;
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
    for (int i = 0; i < Labyrintale.charSelectScreen.chars.length; i++) {
      Labyrintale.charSelectScreen.chars[i].removeChar();
    }
    Labyrintale.charSelectScreen.nextButton.disable();
    Labyrintale.charSelectScreen.backButton.onHide();
    Labyrintale.charSelectScreen.nextButton.onHide();
    charSelectScreen.seedText.text = "";
    fadeOutAndChangeScreen(charSelectScreen, 1.0f);
  }
}
