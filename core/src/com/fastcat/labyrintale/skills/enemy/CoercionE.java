package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.CoercionAction;

public class CoercionE extends AbstractSkill {

  private static final String ID = "BattleE";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

  public CoercionE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
  }

  @Override
  public void use() {
    bot(new CoercionAction(owner, true));
  }

  @Override
  protected void upgradeCard() {}
}
