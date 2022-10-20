package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class ProtectE extends AbstractSkill {

  private static final String ID = "ProtectE";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.BOTH;
  private static final int VALUE = 3;

  public ProtectE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new BlockAction(this.owner, target, spell));
  }

  @Override
  protected void upgradeCard() {}
}
