package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BlindStatus;

public class EyeSting extends AbstractSkill {

  private static final String ID = "EyeSting";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
  private static final int ATTACK = 5;
  private static final int UP = 5;

  public EyeSting(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(ATTACK, UP);
    cost = 3;
  }

  @Override
  public void use() {
    AbstractAction a = new AttackAction(owner, target, attack, AttackAction.AttackType.LIGHT);
    bot(a);
    AbstractAction m = new ApplyStatusAction(new BlindStatus(), owner, target, true);
    m.preAction = a;
    bot(m);
  }

  @Override
  protected void upgradeCard() {}
}
