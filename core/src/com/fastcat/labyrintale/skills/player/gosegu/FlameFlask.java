package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.FlawStatus;

public class FlameFlask extends AbstractSkill {

  private static final String ID = "FlameFlask";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST_TWO;
  private static final int VALUE = 1;

  public FlameFlask(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new FlawStatus(value), owner, target, false));
  }

  @Override
  protected void upgradeCard() {}
}
