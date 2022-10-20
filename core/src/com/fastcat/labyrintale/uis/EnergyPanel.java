package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.KeyString;

public class EnergyPanel extends AbstractUI {

  public EnergyPanel() {
    super(FileHandler.getUi().get("ENERGY_ORB"));
    fontData = FontHandler.ENERGY;
    clickable = false;
    KeyString.KeyData k = StringHandler.keyString.get("Energy");
    subs.add(new SubText(k.NAME, k.DESC));
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (AbstractLabyrinth.energy == 0) sb.setColor(Color.GRAY);
      else sb.setColor(Color.WHITE);
      sb.draw(img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
      FontHandler.renderCenter(
          sb,
          fontData,
          AbstractLabyrinth.energy + "/" + AbstractLabyrinth.MAX_ENERGY,
          x,
          y + sHeight / 2,
          sWidth,
          sHeight);
    }
  }

  @Override
  protected Array<SubText> getSubText() {
    return subs;
  }
}
