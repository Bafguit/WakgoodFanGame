package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HealAction;

public class BurgerHat extends AbstractItem {

  private static final String ID = "BurgerHat";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public BurgerHat(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(new HealAction(owner, AbstractSkill.SkillTarget.SELF, 1, false));
  }
}
