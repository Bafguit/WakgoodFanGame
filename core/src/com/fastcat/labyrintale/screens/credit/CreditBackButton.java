package com.fastcat.labyrintale.screens.credit;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

import static com.fastcat.labyrintale.Labyrintale.mainMenuScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class CreditBackButton extends AbstractUI {

  public CreditBackButton() {
    super(FileHandler.getUi().get("BACK"));
    setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
    fontData = MAIN_MENU;
    text = "나가기";
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
    SoundHandler.fadeOutAll();
    mainMenuScreen.playMusic = true;
    Labyrintale.fadeOutAndChangeScreen(mainMenuScreen, 1.5f);
  }
}
