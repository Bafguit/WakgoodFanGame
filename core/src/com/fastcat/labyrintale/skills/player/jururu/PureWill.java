package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.PurifyAction;
import com.fastcat.labyrintale.status.DebuResPlusStatus;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class PureWill extends AbstractSkill {

  private static final String ID = "PureWill";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 2;

  public PureWill(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {
  }

  @Override
  public void onTarget(AbstractEntity e) {
    top(new ApplyStatusAction(new DebuResPlusStatus(value), owner, e, true));
    top(new PurifyAction(owner, e));
  }

  @Override
  protected void upgradeCard() {}
}
