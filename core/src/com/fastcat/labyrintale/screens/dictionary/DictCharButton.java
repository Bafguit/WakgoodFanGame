package com.fastcat.labyrintale.screens.dictionary;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class DictCharButton extends AbstractUI {


  public DictCharGroup group;
  public DictScreen select;

  public DictCharButton(AbstractPlayer p, DictScreen s) {
    super(FileHandler.getUi().get("BORDER_PL"));
    group = new DictCharGroup(p);
    this.select = s;
    clickable = false;
  }

  @Override
  protected void updateButton() {
    clickable = select.cSelected != this;
    if(!clickable) {
      group.update();
    }
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (!clickable || over) sb.setColor(Color.WHITE);
      else sb.setColor(Color.LIGHT_GRAY);
      sb.draw(group.data.player.img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
      sb.draw(img, x, y, sWidth, sHeight);

      if(!clickable) {
        group.render(sb);
      }
    }
  }

  @Override
  protected void onOver() {}

  @Override
  protected void onClick() {
    select.cSelected = this;
  }
}
