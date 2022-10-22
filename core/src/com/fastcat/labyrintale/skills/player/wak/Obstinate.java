package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.ShieldPushAction;
import com.fastcat.labyrintale.status.NeutResPlusStatus;

public class Obstinate extends AbstractSkill {

  private static final String ID = "Obstinate";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
  private static final int VALUE = 2;

  public Obstinate(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE);
    setBaseCost(1, -1);
  }

  @Override
  public void use() {
    bot(new ShieldPushAction(this, target));
    bot(new ApplyStatusAction(new NeutResPlusStatus(value), owner, owner, true));
  }

  @Override
  protected void upgradeCard() {}

  @Override
  public int calculateAttack(int a) {
    return a + owner.block;
  }
}
