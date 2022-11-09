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
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
  private static final int VALUE = 2;

  public ConfidPotion(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new SpeedPlusStatus(value), owner, TARGET, true));
    bot(new ApplyStatusAction(new InfectionStatus(2), owner, TARGET, true));
  }

  @Override
  protected void upgradeCard() {}
}
