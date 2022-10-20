package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.InfectionStatus;

public class UpgradePotion extends AbstractSkill {

  private static final String ID = "UpgradePotion";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 1;

  public UpgradePotion(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new ApplyStatusAction(new InfectionStatus(2), owner, e, true));
    top(new ApplyStatusAction(new AttackStatus(value), owner, e, true));
  }

  @Override
  protected void upgradeCard() {}
}
