package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class BattleAxe extends AbstractItem {

  private static final String ID = "BattleAxe";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public BattleAxe(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
    int h = (int) ((float) damage * 0.25f);
    if(h > 0) top(new HealAction(owner, owner, h));
  }
}
