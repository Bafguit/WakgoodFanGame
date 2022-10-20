package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.LethargyStatus;

public class NegativeSkill extends AbstractSkill {

  private static final String ID = "negative";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ADVISOR;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
  private static final int VALUE = 1;

  public NegativeSkill() {
    super(ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE);
    passive = true;
  }

  @Override
  public void use() {}

  @Override
  public void atBattleStart() {
    bot(new ApplyStatusAction(new AttackStatus(value), owner, target, true));
    bot(new ApplyStatusAction(new LethargyStatus(value), owner, target, true));
  }

  @Override
  protected void upgradeCard() {}
}
