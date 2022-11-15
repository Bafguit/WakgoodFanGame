package com.fastcat.labyrintale.screens.resultscreen;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU_BORDER;

public class BackToMainButton extends AbstractUI {

  public BackToMainButton() {
    super(FileHandler.getUi().get("NEXT"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.05f);
    fontData = MAIN_MENU_BORDER;
    text = "메인 메뉴";
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
    Labyrintale.mainMenuScreen.onCreate();
    Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
  }
}
