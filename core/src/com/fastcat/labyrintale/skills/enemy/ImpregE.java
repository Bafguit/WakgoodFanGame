package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.FixedStatus;
import com.fastcat.labyrintale.status.ProvokeStatus;

public class ImpregE extends AbstractSkill {

  private static final String ID = "ImpregE";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.NONE;

  public ImpregE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
  }

  @Override
  public void use() {
    bot(new MoveAction(owner, owner, 0, 0.3f));
    bot(new ApplyStatusAction(new ProvokeStatus(owner), owner, owner, true));
    bot(new ApplyStatusAction(new FixedStatus(), owner, owner, true));
  }

  @Override
  protected void upgradeCard() {}
}
