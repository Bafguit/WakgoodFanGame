package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

public class ExitGameButton extends AbstractUI {

  public ExitGameButton(SettingScreen sc) {
    super(FileHandler.getUi().get("BUTTON"));
    setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f);
    fontData = BUTTON;
    text = "종료";
    screen = sc;
  }

  @Override
  protected void onClick() {
    SettingHandler.save();
    Gdx.app.exit();
  }
}
