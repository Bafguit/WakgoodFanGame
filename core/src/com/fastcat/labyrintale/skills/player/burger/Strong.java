package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.status.FixedStatus;
import com.fastcat.labyrintale.status.ResistPlusStatus;

public class Strong extends AbstractSkill {

  private static final String ID = "Strong";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 5;

  public Strong(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
    setBaseValue(1, 1);
  }

  @Override
  public void use() {
    bot(new BlockAction(owner, owner, spell));
    bot(new ApplyStatusAction(new ResistPlusStatus(value), owner, owner, true));
  }

  @Override
  protected void upgradeCard() {}
}
