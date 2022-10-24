package com.fastcat.labyrintale.items.gold;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class FireStaff extends AbstractItem {

  private static final String ID = "FireStaff";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public FireStaff(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(10);
    owner.stat.debuRes += 10;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-10);
    owner.stat.debuRes -= 10;
  }

  @Override
  public void onApplyStatus(AbstractStatus s, Array<AbstractEntity> target) {
    if(s.type == AbstractStatus.StatusType.DEBUFF && s.hasAmount) {
      flash();
      s.amount++;
    }
  }
}
