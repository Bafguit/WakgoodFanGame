package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.ResistMinusStatus;

public class OldPistol extends AbstractItem {

  private static final String ID = "OldPistol";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public OldPistol(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  public void onMove(AbstractEntity source) {
    top(new ApplyStatusAction(new CourageStatus(2), owner, owner, true));
  }
}
