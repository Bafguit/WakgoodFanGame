package com.fastcat.labyrintale.screens.runview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class RunViewItemIcon extends AbstractUI {

  private final Sprite border = FileHandler.getUi().get("BORDER_R");
  public AbstractItem skill;

  public RunViewItemIcon() {
    super(FileHandler.getUi().get("BORDER"));
    clickable = false;
    subWay = SubText.SubWay.DOWN;
  }

  public void setItem(AbstractItem item) {
    skill = item;
  }

  @Override
  protected Array<SubText> getSubText() {
    return skill != null ? skill.key : null;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled && skill != null) {
      sb.setColor(Color.WHITE);
      if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
      sb.setColor(skill.getRarityColor());
      sb.draw(
          skill.rarity == AbstractItem.ItemRarity.STARTER ? border : img, x, y, sWidth, sHeight);
    }
  }
}
