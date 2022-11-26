package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.CriticalMinusStatus;
import com.fastcat.labyrintale.status.InfectionStatus;

public class UpgradePotion extends AbstractSkill {

  private static final String ID = "UpgradePotion";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 8;

  public UpgradePotion(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 2);
    setBaseCost(3);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new ApplyStatusAction(new CourageStatus(value), owner, e, false));
  }

  @Override
  protected void upgradeCard() {}
}
