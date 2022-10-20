package com.fastcat.labyrintale.screens.skillselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen.SkillSelectGroup;

public class SkillSelectCharIcon extends AbstractUI {

  private final Sprite border;

  private final SkillSelectGroup group;
  public AbstractPlayer p;

  public SkillSelectCharIcon(SkillSelectGroup g, AbstractPlayer p) {
    super(FileHandler.getUi().get("BORDER_M"));
    group = g;
    this.p = p;
    border = FileHandler.getCharImg().get(p.playerClass);
    clickable = false;
  }

  @Override
  protected void updateButton() {}

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (group.bg.over) sb.setColor(Color.WHITE);
      else sb.setColor(Color.LIGHT_GRAY);
      if (showImg) sb.draw(border, x, y, sWidth, sHeight);
      sb.draw(img, x, y, sWidth, sHeight);
    }
  }

  @Override
  protected void onClick() {}

  public void removeChar() {}
}
