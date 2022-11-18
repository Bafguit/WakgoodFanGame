package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class LogoText extends AbstractUI {

  public LogoText() {
    super(FileHandler.getUi().get("TITLE"));
    setPosition(Gdx.graphics.getWidth() * 0.75f - sWidth / 2, Gdx.graphics.getHeight() * 0.6f);
    overable = false;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      sb.setColor(Color.WHITE);
      sb.draw(img, x, y, sWidth, sHeight);
    }
  }
}
