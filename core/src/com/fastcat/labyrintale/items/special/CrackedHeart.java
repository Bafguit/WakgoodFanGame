package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ModifyMaxHealthAction;

public class CrackedHeart extends AbstractItem {

  private static final String ID = "CrackedHeart";
  private static final ItemRarity RARITY = ItemRarity.SPECIAL;

  public CrackedHeart(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleEnd() {
    flash();
    bot(new ModifyMaxHealthAction(owner, AbstractSkill.SkillTarget.SELF, 2));
  }
}
