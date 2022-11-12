package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;

public class FourE extends AbstractSkill {

  private static final String ID = "FourE";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
  private static final int VALUE = 1;

  public FourE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setIntent(IntentType.ATTACK);
  }

  @Override
  public void use() {
    AbstractAction a;
    bot(a = new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHT, true));
    for (int i = 0; i < 3; i++) {
      bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHT, true), a);
    }
  }

  @Override
  protected void upgradeCard() {}
}
