package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class Boost extends AbstractSkill {

  private static final String ID = "Boost";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;

  public Boost(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseCost(5);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new BlockAction(owner, e, owner.block));
  }

  @Override
  protected void upgradeCard() {
    if (cost > 0) cost--;
  }
}
