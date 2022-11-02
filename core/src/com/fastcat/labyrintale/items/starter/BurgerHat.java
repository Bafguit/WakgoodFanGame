package com.fastcat.labyrintale.items.starter;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
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
    top(new HealAction(owner, AbstractSkill.SkillTarget.SELF, 1));
  }

  public void endOfTurn() {
    Array<AbstractEntity> temp = new Array<>();
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      if(p.block > 0) temp.add(p);
    }
    if(temp.size > 0) {
      top(new HealAction(owner, temp, 1));
    }
  }
}
