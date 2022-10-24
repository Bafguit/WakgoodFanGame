package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HealAction;

public class Pendant extends AbstractItem {

  private static final String ID = "Pendant";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public Pendant(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleEnd() {
    flash();
    bot(new HealAction(owner, AbstractSkill.SkillTarget.SELF, 1));
  }
}
