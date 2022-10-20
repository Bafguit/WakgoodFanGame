package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.PurifyAction;

public class Purify extends AbstractSkill {

  private static final String ID = "Purify";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 2;

  public Purify(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new PurifyAction(owner, e));
    top(new HealAction(owner, e, spell));
  }

  @Override
  protected void upgradeCard() {}
}
