package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;
import com.fastcat.labyrintale.status.InfectionStatus;
import com.fastcat.labyrintale.status.SpeedPlusStatus;

public class ConfidPotion extends AbstractSkill {

  private static final String ID = "ConfidPotion";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 2;

  public ConfidPotion(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
    setBaseCost(3);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new ApplyStatusAction(new InfectionStatus(3), owner, e, true));
    top(new ApplyStatusAction(new SpeedPlusStatus(value), owner, e, true));
  }

  @Override
  protected void upgradeCard() {}
}
