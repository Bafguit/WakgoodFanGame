package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.credit.CreditScreen;
import com.fastcat.labyrintale.screens.runview.RunViewScreen;

import java.util.Properties;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class CreditButton extends AbstractUI {

  public CreditButton() {
    super(FileHandler.getUi().get("MENU_SELECT"));
    setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.19f);
    fontData = MAIN_MENU;
    text = "크레딧";
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
    Labyrintale.fadeOutAndChangeScreen(new CreditScreen(), 1.5f);
  }
}
