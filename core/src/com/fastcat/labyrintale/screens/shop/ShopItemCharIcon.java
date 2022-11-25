package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rooms.other.ShopRoom;

public class ShopItemCharIcon extends AbstractUI {

  public ShopRoom.SkillItem item;

  public ShopItemCharIcon(ShopRoom.SkillItem re) {
    super(FileHandler.getUi().get("BORDER_S"));
    item = re;
    clickable = false;
    overable = false;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled && !item.isDone) {
      boolean can = item.canBuy();
      if (!can) sb.setColor(Color.DARK_GRAY);
      else sb.setColor(Color.WHITE);
      sb.draw(item.skill.owner.img, x, y, sWidth, sHeight);
      sb.draw(img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
    }
  }

  @Override
  protected void updateButton() {}
}
