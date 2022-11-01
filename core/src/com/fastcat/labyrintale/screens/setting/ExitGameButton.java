package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class ExitGameButton extends AbstractUI {

  public ExitGameButton(SettingScreen sc) {
    super(FileHandler.getUi().get("NEXT"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.7f);
    fontData = MAIN_MENU;
    text = "종료";
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
    logger.log("Shutting Down...");
    Gdx.app.exit();
  }
}
