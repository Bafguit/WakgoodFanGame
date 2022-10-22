package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class ShockBomb extends AbstractSkill {

  private static final String ID = "ShockBomb";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY;
  private static final int VALUE = 3;

  public ShockBomb(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseValue(1, 1);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new MoveAction(e, owner, 3, 0.15f));
    top(new ApplyStatusAction(new UnfortifiedStatus(value), owner, e, true));
    top(new MoveAction(owner, owner, 3, 0.15f));
    top(new AttackAction(owner, e, attack, AttackAction.AttackType.HEAVY, true));
  }

  @Override
  protected void upgradeCard() {}
}
