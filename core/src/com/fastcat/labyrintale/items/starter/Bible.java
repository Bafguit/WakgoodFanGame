package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.ResistMinusStatus;
import com.fastcat.labyrintale.status.ResistPlusStatus;

public class Bible extends AbstractItem {

  private static final String ID = "Bible";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public Bible(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }
}
