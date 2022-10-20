package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.MoveAction;

public class MoveRightE extends AbstractSkill {

  private static final String ID = "MoveRight";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.BASIC;
  private static final SkillTarget TARGET = SkillTarget.SELF;
  private final AbstractEnemy enemy;

  public MoveRightE(AbstractEnemy e) {
    super(e, ID, TYPE, RARITY, TARGET);
    enemy = e;
  }

  @Override
  public void use() {
    bot(new MoveAction(enemy, owner, false));
  }

  @Override
  protected boolean available() {
    return enemy.index < 3;
  }

  @Override
  protected void upgradeCard() {}
}
