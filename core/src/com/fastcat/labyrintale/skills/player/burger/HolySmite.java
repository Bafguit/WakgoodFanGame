package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HolySmiteAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class HolySmite extends AbstractSkill {

  private static final String ID = "HolySmite";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
  private static final int ATK = 1;

  public HolySmite(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(ATK, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {
    bot(new HolySmiteAction(owner, target, attack));
  }

  @Override
  protected void upgradeCard() {}
}
