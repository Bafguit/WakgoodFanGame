package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CloseTempScreenButton extends AbstractUI {

  public CloseTempScreenButton(AbstractScreen screen) {
    super(FileHandler.getUi().get("NEXT"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
    fontData = MAIN_MENU;
    text = "닫기";
    showImg = false;
    this.screen = screen;
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
    Labyrintale.removeTempScreen(screen);
  }
}
