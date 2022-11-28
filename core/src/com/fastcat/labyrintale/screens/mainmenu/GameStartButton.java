package com.fastcat.labyrintale.screens.mainmenu;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class GameStartButton extends AbstractUI {

  public GameStartButton() {
    super(FileHandler.getUi().get("MENU_SELECT"));
    setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.45f);
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
    fadeOutAndChangeScreen(diffScreen);
  }
}
