package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.LilpaaAction;
import com.fastcat.labyrintale.status.InfectionStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Lilpaa extends AbstractSkill {

  private static final String ID = "Lilpaa";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
  private static final int ATTACK = 6;
  private static final int UP = 2;

  public Lilpaa(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(ATTACK, UP);
    setBaseValue(2, UP);
    setBaseCost(3);
  }

  @Override
  public void use() {
    bot(new AttackAction(owner, target, attack, AttackAction.AttackType.LIGHTNING, true));
    bot(new ApplyStatusAction(new UnfortifiedStatus(value), owner, TARGET, true));
  }

  @Override
  protected void upgradeCard() {}
}
