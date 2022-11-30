package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.CriticalPlusStatus;

public class Impulse extends AbstractSkill {

  private static final String ID = "Impulse";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.SELF;
  private static final int VALUE = 3;

  public Impulse(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new MoveAction(owner, owner, 0, 0.2f));
    bot(new BlockAction(owner, owner, spell));
    bot(new ApplyStatusAction(new CourageStatus(value), owner, target, true));
  }

  @Override
  protected void upgradeCard() {}
}
