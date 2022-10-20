package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoScreen;

public class MapButton extends AbstractUI {

  public boolean isShowing;

  public MapButton() {
    super(FileHandler.getUi().get("MAP"));
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      sb.setColor(isShowing || over ? Color.WHITE : Color.LIGHT_GRAY);
      sb.draw(img, x, y, sWidth, sHeight);
    }
  }

  @Override
  protected void onClick() {
    if (!isShowing) {
      MapScreen.view();
      isShowing = true;
      if (playerInfoScreen.showing) {
        PlayerInfoScreen.remove();
      }
    } else {
      MapScreen.remove();
      isShowing = false;
    }
  }
}
