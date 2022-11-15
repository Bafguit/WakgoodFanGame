package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class AvantProtect extends AbstractSkill {

  private static final String ID = "AvantProtect";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;

  public AvantProtect(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(8);
    setBaseValue(2, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {
    bot(new BlockAction(owner, target, spell));
  }

  @Override
  protected void upgradeCard() {}

  @Override
  public int calculateSpell(int a) {
    return a + owner.stat.speed * (value - 1);
  }
}
