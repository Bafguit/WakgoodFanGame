package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class RuseokSkill extends AbstractSkill {

  private static final String ID = "ruseok";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ADVISOR;
  private static final SkillTarget TARGET = SkillTarget.NONE;

  public RuseokSkill() {
    super(ID, TYPE, RARITY, TARGET);
    passive = true;
  }

  @Override
  public void use() {}

  @Override
  protected void upgradeCard() {}
}
