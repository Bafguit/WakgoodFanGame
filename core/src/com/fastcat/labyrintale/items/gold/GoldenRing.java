package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class GoldenRing extends AbstractItem {

  private static final String ID = "GoldenRing";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public GoldenRing(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(10);
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-10);
  }

  @Override
  public void atBattleStart() {
    flash();
    top(new BlockAction(owner, AbstractSkill.SkillTarget.PLAYER_ALL, 10));
  }
}
