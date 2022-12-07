package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

public class SettingButton extends AbstractUI {

  public SettingButton(float x, float y) {
    super(FileHandler.getUi().get("SETTING_B"), x, y);
  }

  @Override
  protected void onClick() {
    if (!Labyrintale.setting) {
      Labyrintale.openSetting();
    }
  }
}
