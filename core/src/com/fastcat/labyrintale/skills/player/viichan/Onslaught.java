package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;

public class Onslaught extends AbstractSkill {

  private static final String ID = "Onslaught";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
  private static final int VALUE = 3;

  public Onslaught(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {
    AbstractAction a;
    bot(a = new AttackAction(owner, target, attack, AttackAction.AttackType.SLASH_H, true));
    bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SLASH_V, true), a);
  }

  @Override
  protected void upgradeCard() {}
}
