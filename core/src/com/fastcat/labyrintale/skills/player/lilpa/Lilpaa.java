package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.LilpaaAction;

public class Lilpaa extends AbstractSkill {

  private static final String ID = "Lilpaa";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
  private static final int ATTACK = 5;
  private static final int UP = 2;

  public Lilpaa(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(ATTACK, UP);
    setBaseValue(2, UP);
    setBaseCost(3);
  }

  @Override
  public void use() {
    bot(new LilpaaAction(this));
  }

  @Override
  protected void upgradeCard() {}
}
