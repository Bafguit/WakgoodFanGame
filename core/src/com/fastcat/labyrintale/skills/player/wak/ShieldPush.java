package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.ShieldPushAction;

public class ShieldPush extends AbstractSkill {

  private static final String ID = "ShieldPush";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.STARTER;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
  private static final int VALUE = 3;

  public ShieldPush(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
    setBaseAttack(0);
  }

  @Override
  public void use() {
    bot(new BlockAction(owner, owner, spell));
    bot(new ShieldPushAction(this, target));
  }

  @Override
  protected void upgradeCard() {}

  @Override
  public int calculateAttack(int a) {
    return a + owner.block;
  }
}
