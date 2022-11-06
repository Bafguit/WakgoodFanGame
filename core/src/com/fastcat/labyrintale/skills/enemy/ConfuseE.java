package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BlindStatus;
import com.fastcat.labyrintale.status.UnblockableStatus;

public class ConfuseE extends AbstractSkill {

  private static final String ID = "ConfuseE";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

  public ConfuseE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
  }

  @Override
  public void use() {
    AbstractAction a;
    bot(a = new ApplyStatusAction(new UnblockableStatus(), owner, SkillTarget.PLAYER_FIRST_TWO, true));
    bot(new ApplyStatusAction(new BlindStatus(), owner, SkillTarget.PLAYER_LAST_TWO, true), a);
  }

  @Override
  protected void upgradeCard() {}
}
