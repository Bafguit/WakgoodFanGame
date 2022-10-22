package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.PurifyAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class Calmness extends AbstractSkill {

  private static final String ID = "Calmness";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.SELF;
  private static final int VALUE = 3;

  public Calmness(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new CourageStatus(value), owner, target, false));
    bot(new PurifyAction(owner, owner));
  }

  @Override
  protected void upgradeCard() {}
}
