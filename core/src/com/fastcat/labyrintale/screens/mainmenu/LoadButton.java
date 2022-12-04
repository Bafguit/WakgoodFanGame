package com.fastcat.labyrintale.screens.mainmenu;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU_SHADOW;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

public class LoadButton extends AbstractUI {

  public LoadButton() {
    super(FileHandler.getUi().get("MENU_SELECT"));
    setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.385f);
    fontData = MAIN_MENU_SHADOW;
    text = "불러오기";
    showImg = false;
    overable = SaveHandler.hasSave;
  }

  @Override
  protected void updateButton() {
    overable = SaveHandler.hasSave;
    if (!over && showImg) showImg = false;
  }

  @Override
  protected void onOver() {
    if(overable) showImg = true;
  }

  @Override
  protected void onClick() {
    Labyrintale.fadeOutAndChangeScreen(new LoadingScreen(false));
  }
}
