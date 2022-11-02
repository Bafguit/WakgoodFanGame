package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class ItemPanel extends AbstractUI {

  private Sprite psv = FileHandler.getUi().get("BORDER_R");
  public AbstractItem item;

  public ItemPanel() {
    super(FileHandler.getUi().get("BORDER_M"));
    clickable = false;
  }

  @Override
  protected void updateButton() {
    if (over && item != null) {
      AbstractLabyrinth.cPanel.infoPanel.setInfo(item);
    }
  }

  @Override
  protected Array<SubText> getSubText() {
    return item != null ? item.key : null;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled && item != null) {
      sb.setColor(Color.WHITE);
      if (item != null) {
        sb.draw(item.img, x, y, sWidth, sHeight);
        sb.draw(item.rarity == AbstractItem.ItemRarity.STARTER ? psv : img, x, y, sWidth, sHeight);
      }
    }
  }
}
