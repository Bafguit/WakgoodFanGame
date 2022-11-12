package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class ChargeE extends AbstractSkill {

  private static final String ID = "ChargeE";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
  private static final int VALUE = 3;

  public ChargeE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseSpell(3, 1);
    setIntent(IntentType.ATTACK_SHIELD);
  }

  @Override
  public void use() {
    bot(new MoveAction(owner, owner, true, 0.05f));
    bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH, true));
    bot(new BlockAction(this.owner, SkillTarget.SELF, spell));
  }

  @Override
  protected void upgradeCard() {}
}
