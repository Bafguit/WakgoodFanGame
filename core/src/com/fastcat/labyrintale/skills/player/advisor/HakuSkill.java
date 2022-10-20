package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.CourageStatus;

public class HakuSkill extends AbstractSkill {

  private static final String ID = "haku";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ADVISOR;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
  private static final int VALUE = 2;

  public HakuSkill() {
    super(ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE);
  }

  @Override
  public void use() {
    ActionHandler.bot(new ApplyStatusAction(new CourageStatus(value), owner, target, false));
  }

  @Override
  protected void upgradeCard() {}
}
