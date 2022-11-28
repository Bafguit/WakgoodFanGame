package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class LogoText extends AbstractUI {

  public LogoText() {
    super(FileHandler.getUi().get("TITLE"));
    setPosition(1350 * scale, 740 * scale);
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
