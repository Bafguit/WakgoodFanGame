package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;

public class AllTwoE extends AbstractSkill {

  private static final String ID = "AllTwoE";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
  private static final int VALUE = 1;

  public AllTwoE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setIntent(IntentType.ATTACK);
  }

  @Override
  public void use() {
    bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.SLASH_H, true));
    bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.SLASH_H, true));
  }

  @Override
  protected void upgradeCard() {}
}
