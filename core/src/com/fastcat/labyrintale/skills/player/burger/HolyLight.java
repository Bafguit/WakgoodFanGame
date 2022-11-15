package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.InfectionStatus;
import com.fastcat.labyrintale.status.SinStatus;

public class HolyLight extends AbstractSkill {

  private static final String ID = "HolyLight";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ALL;
  private static final int VALUE = 3;

  public HolyLight(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
    setBaseValue(VALUE, 1);
    setBaseCost(3);
  }

  @Override
  public void use() {
    bot(new HealAction(owner, SkillTarget.PLAYER_ALL, spell));
    bot(new ApplyStatusAction(new SinStatus(value), owner, SkillTarget.ENEMY_ALL, true));
  }

  @Override
  protected void upgradeCard() {}
}
