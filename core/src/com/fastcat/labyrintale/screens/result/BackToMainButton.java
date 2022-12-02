package com.fastcat.labyrintale.screens.result;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

public class BackToMainButton extends AbstractUI {

  public BackToMainButton() {
    super(FileHandler.getUi().get("BUTTON"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.05f);
    fontData = BUTTON;
    text = "메인 메뉴";
  }

  @Override
  protected void onClick() {
    Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
  }
}
