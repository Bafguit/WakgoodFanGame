package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.status.ArmourStatus;
import com.fastcat.labyrintale.status.BlindStatus;

public class ShoulderPlate extends AbstractItem {

  private static final String ID = "ShoulderPlate";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public ShoulderPlate(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleStart() {
    flash();
    top(new ApplyStatusAction(new ArmourStatus(5), owner, owner, true));
  }
}
