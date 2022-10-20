package com.fastcat.labyrintale.screens.rest;

import static com.fastcat.labyrintale.handlers.FontHandler.REST_DESC;
import static com.fastcat.labyrintale.handlers.FontHandler.renderColorCenter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class RestDesc extends AbstractUI {

  public RestDesc(String d) {
    super(FileHandler.getUi().get("WAY_SELECT"));
    text = d;
    showImg = false;
    fontData = REST_DESC;
    overable = false;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (!over) sb.setColor(Color.LIGHT_GRAY);
      else sb.setColor(Color.WHITE);

      if (fontData != null) {
        renderColorCenter(sb, fontData, text, x, y + sHeight / 2, sWidth);
      }
    }
  }
}
