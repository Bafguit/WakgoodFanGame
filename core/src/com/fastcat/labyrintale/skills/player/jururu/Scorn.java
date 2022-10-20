package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.LethargyStatus;

public class Scorn extends AbstractSkill {

  private static final String ID = "Scorn";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST_TWO;
  private static final int VALUE = 1;

  public Scorn(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new LethargyStatus(value), owner, target, false));
  }

  @Override
  protected void upgradeCard() {}
}
