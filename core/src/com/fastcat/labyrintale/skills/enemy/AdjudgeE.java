package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.FixedStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class AdjudgeE extends AbstractSkill {

  private static final String ID = "AdjudgeE";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;

  public AdjudgeE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(1, 1);
    setBaseValue2(2);
    setIntent(IntentType.DEBUFF);
  }

  @Override
  public void use() {
    AbstractAction a;
    bot(a = new ApplyStatusAction(new FixedStatus(value2), owner, target, true));
    bot(new ApplyStatusAction(new UnfortifiedStatus(value), owner, target, true), a);
  }

  @Override
  protected void upgradeCard() {}
}
