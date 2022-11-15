package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.CriticalPlusStatus;
import com.fastcat.labyrintale.status.SpeedPlusStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Bless extends AbstractSkill {

  private static final String ID = "Bless";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 3;

  public Bless(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
    setBaseValue(value, 1);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new ApplyStatusAction(new CourageStatus(value), owner, e, true));
    top(new HealAction(owner, e, spell));
  }

  @Override
  protected void upgradeCard() {}
}
