package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.CriticalPlusStatus;
import com.fastcat.labyrintale.status.DebuResPlusStatus;

public class Channeling extends AbstractSkill {

  private static final String ID = "Channeling";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_LAST;
  private static final int VALUE = 2;

  public Channeling(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new CourageStatus(value), owner, owner, true));
    bot(new ApplyStatusAction(new CriticalPlusStatus(value), owner, owner, true));
    bot(new ApplyStatusAction(new DebuResPlusStatus(value), owner, owner, true));
  }

  @Override
  protected void upgradeCard() {}
}
