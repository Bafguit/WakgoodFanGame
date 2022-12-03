package com.fastcat.labyrintale.screens.achieve;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.dictionary.DictScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.CLOSE;

public class AchvCloseButton extends AbstractUI {

  public AchvCloseButton(AchieveScreen sc) {
    super(FileHandler.getUi().get("CLOSE_SET"));
    setPosition(Gdx.graphics.getWidth() * 0.85f - sWidth / 2, Gdx.graphics.getHeight() * 0.795f - sHeight / 2);
    fontData = CLOSE;
    text = "×";
    screen = sc;
  }

  @Override
  protected void onClick() {
    Labyrintale.removeTempScreen(screen);
  }
}
