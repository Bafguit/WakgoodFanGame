package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;

public class DiaSword extends AbstractSkill {

  private static final String ID = "DiaSword";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.STARTER;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
  private static final int VALUE = 7;

  public DiaSword(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {
    bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SLASH_D));
  }

  @Override
  protected void upgradeCard() {}
}
