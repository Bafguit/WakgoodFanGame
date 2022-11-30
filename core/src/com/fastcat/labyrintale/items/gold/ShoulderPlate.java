package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
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
  public void onGain() {
    owner.modifyMaxHealth(20);
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-20);
  }

  @Override
  public int onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
    if(damage > 0 && (type == AbstractEntity.DamageType.NORMAL || type == AbstractEntity.DamageType.COUNTER)) {
      return damage - 1;
    }
    return damage;
  }
}
