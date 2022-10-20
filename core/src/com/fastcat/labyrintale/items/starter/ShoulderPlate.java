package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class ShoulderPlate extends AbstractItem {

  private static final String ID = "ShoulderPlate";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public ShoulderPlate(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(new BlockAction(owner, AbstractSkill.SkillTarget.SELF, 2));
  }
}
