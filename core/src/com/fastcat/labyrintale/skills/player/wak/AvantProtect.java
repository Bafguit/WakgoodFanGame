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
    setBaseValue(0);
    setBaseCost(2);
  }

  @Override
  public void use() {
    bot(new BlockAction(owner, target, spell));
  }

  @Override
  protected void upgradeCard() {
    if (cost > 0) cost--;
  }

  @Override
  public int calculateValue(int a) {
    for (AbstractPlayer p : AbstractLabyrinth.players) {
      if (p.index != 0 && p.isAlive()) a += p.block;
    }
    return a;
  }
}
