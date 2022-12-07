package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;
import static com.fastcat.labyrintale.handlers.FontHandler.CLOSE;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class CloseSettingButton extends AbstractUI {

  public CloseSettingButton(SettingScreen sc) {
    super(FileHandler.getUi().get("BUTTON"));
    setPosition(Gdx.graphics.getWidth() * 0.79f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f);
    fontData = BUTTON;
    text = "저장";
    screen = sc;
  }

  @Override
  protected void onClick() {
    SettingHandler.save();
    Labyrintale.closeSetting();
  }
}
