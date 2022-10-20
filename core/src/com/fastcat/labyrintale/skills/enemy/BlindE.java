package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BlindStatus;

public class BlindE extends AbstractSkill {

  private static final String ID = "BlindE";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_LAST_TWO;

  public BlindE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new BlindStatus(), owner, target, false));
  }

  @Override
  protected void upgradeCard() {}
}
