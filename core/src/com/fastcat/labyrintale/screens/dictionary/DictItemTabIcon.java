package com.fastcat.labyrintale.screens.dictionary;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;

import java.util.HashMap;

public class DictItemTabIcon extends AbstractUI {

  private final HashMap<String, Boolean> map = UnlockHandler.achvs.get(UnlockHandler.Unlocks.ITEM);
  private final Sprite locked = FileHandler.getUi().get("UNKNOWN");
  public DictGroup group;
  public AbstractItem item;

  public DictItemTabIcon(DictGroup group, AbstractItem item) {
    super(FileHandler.getUi().get("BORDER_M"));
    this.group = group;
    clickable = false;
    overable = false;
    this.item = item;
  }

  @Override
  protected void updateButton() {
    if (item != null) {
      overable = map.get(item.id);
      if(overable && over) {
        group.setItem(item);
      }
    }
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled && item != null) {
      sb.setColor(Color.WHITE);
      sb.draw(!overable ? locked : item.img, x, y, sWidth, sHeight);
      sb.setColor(item.getRarityColor());
      sb.draw(img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
    }
  }

  @Override
  protected Array<SubText> getSubText() {
    return item != null && overable ? item.key : null;
  }
}
