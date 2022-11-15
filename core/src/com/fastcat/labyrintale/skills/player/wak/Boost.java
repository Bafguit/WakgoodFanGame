package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.BoostAction;

public class Boost extends AbstractSkill {

  private static final String ID = "Boost";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

  public Boost(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseCost(4);
  }

  @Override
  public void use() {
    bot(new BoostAction(owner, target));
  }

  @Override
  protected void upgradeCard() {
    if (cost > 0) cost--;
  }
}
