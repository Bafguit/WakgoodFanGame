package com.fastcat.labyrintale.screens.runview;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU_BORDER;

public class BackToMainRunButton extends AbstractUI {

  public BackToMainRunButton() {
    super(FileHandler.getUi().get("BACK"));
    setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.95f - sHeight);
    fontData = MAIN_MENU_BORDER;
    text = "뒤로";
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
    Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
  }
}
