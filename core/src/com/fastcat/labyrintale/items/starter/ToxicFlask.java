package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.InfectionStatus;

public class ToxicFlask extends AbstractItem {

  private static final String ID = "ToxicFlask";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public ToxicFlask(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  public float onAttackedMultiply(
          AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
    return attacker.hasStatus("Infection") ? 0.7f : 1.0f;
  }
}
