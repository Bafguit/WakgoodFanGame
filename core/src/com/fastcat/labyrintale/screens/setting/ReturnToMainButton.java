package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.SETTING;

public class ReturnToMainButton extends AbstractUI {

  public ReturnToMainButton(SettingScreen sc) {
    super(FileHandler.getUi().get("NEXT"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.8f);
    fontData = SETTING;
    text = "메인 메뉴";
    showImg = false;
    screen = sc;
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
    SettingHandler.save();
    SoundHandler.fadeOutAll();
    Labyrintale.mainMenuScreen.onCreate();
    Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
  }
}
