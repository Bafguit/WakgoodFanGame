package com.fastcat.labyrintale.screens.mainmenu;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU_SHADOW;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class ExitButton extends AbstractUI {

  public ExitButton() {
    super(FileHandler.getUi().get("MENU_SELECT"));
    setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.125f);
    fontData = MAIN_MENU_SHADOW;
    text = "종료";
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
    logger.log("Shutting Down...");
    Gdx.app.exit();
  }
}
