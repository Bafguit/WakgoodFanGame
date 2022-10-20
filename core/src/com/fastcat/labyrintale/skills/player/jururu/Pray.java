package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Pray extends AbstractSkill {

  private static final String ID = "Pray";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.STARTER;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
  private static final int VALUE = 1;

  public Pray(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
    setBaseSpell(VALUE);
    passive = true;
  }

  @Override
  public void use() {}

  @Override
  public void atBattleStart() {
    top(new HealAction(owner, target, spell));
    top(new ApplyStatusAction(new EnduranceStatus(value), owner, target, true));
  }

  @Override
  protected void upgradeCard() {}
}
