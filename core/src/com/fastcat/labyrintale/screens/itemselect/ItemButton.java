package com.fastcat.labyrintale.screens.itemselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class ItemButton extends AbstractUI {

  public AbstractItem item;
  public ItemSelectScreen select;

  public ItemButton(AbstractItem item, ItemSelectScreen select) {
    super(FileHandler.getUi().get("BORDER_M"));
    this.item = item;
    this.select = select;
  }

  @Override
  protected void updateButton() {
    if (over) AbstractLabyrinth.cPanel.infoPanel.setInfo(item);
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (over) sb.setColor(Color.WHITE);
      else sb.setColor(Color.LIGHT_GRAY);
      sb.draw(item.img, x, y, sWidth, sHeight);
      sb.setColor(item.getRarityColor());
      sb.draw(img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
    }
  }

  @Override
  protected Array<SubText> getSubText() {
    return item != null ? item.key : null;
  }

  @Override
  protected void onOver() {}

  @Override
  protected void onClick() {
    select.itemSelected(item);
  }
}
