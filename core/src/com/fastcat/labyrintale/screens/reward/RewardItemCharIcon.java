package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rewards.SkillReward;

public class RewardItemCharIcon extends AbstractUI {

  public SkillReward item;

  public RewardItemCharIcon(SkillReward re) {
    super(FileHandler.getUi().get("BORDER_S"));
    item = re;
    clickable = false;
    overable = false;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    sb.draw(item.skill.owner.imgTiny, x, y, sWidth, sHeight);
    sb.draw(img, x, y, sWidth, sHeight);
  }

  @Override
  protected void updateButton() {}
}
