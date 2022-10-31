package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class RusukSkill extends AbstractSkill {

  private static final String ID = "rusuk";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ADVISOR;
  private static final SkillTarget TARGET = SkillTarget.NONE;

  public RusukSkill() {
    super(ID, TYPE, RARITY, TARGET);
    passive = true;
  }

  @Override
  public void use() {}

  @Override
  protected void upgradeCard() {}
}
