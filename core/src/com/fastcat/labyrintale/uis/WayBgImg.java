package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.way.WayScreen;

public class WayBgImg extends AbstractUI {

  private final WayScreen sc;

  public WayBgImg(WayScreen sc) {
    super(FileHandler.getBg().get("BG_BLACK"));
    setPosition(0, 0);
    img.setAlpha(0.75f);
    mute = true;
    this.sc = sc;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    img.draw(sb);
  }

  @Override
  public void onClick() {
    boolean can = true;
    for (PlayerWayView pv : sc.players) {
      if (pv.clickable && pv.over) {
        can = false;
        break;
      }
    }
    if (can) {
      sc.isSelecting = false;
    }
  }
}
