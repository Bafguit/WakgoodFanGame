package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class SecretSkill extends AbstractSkill {

  private static final String ID = "secret";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ADVISOR;
  private static final SkillTarget TARGET = SkillTarget.NONE;

  public SecretSkill() {
    super(ID, TYPE, RARITY, TARGET);
    passive = true;
    disposable = true;
  }

  @Override
  public void use() {
    usedOnce = true;
  }

  @Override
  protected void upgradeCard() {}
}
