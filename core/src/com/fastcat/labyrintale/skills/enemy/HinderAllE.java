package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HinderAction;

public class HinderAllE extends AbstractSkill {

  private static final String ID = "HinderAllE";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

  public HinderAllE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(4, 1);
    setBaseValue(1, 1);
  }

  @Override
  public void use() {
    bot(new HinderAction(owner, target, attack, value));
  }

  @Override
  protected void upgradeCard() {}
}
