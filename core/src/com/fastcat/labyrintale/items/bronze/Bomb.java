package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Bomb extends AbstractItem {

  private static final String ID = "Bomb";
  private static final ItemRarity RARITY = ItemRarity.BRONZE;

  public Bomb(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.critical += 10;
  }

  @Override
  public void onRemove() {
    owner.stat.critical -= 10;
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(new ApplyStatusAction(new BurnStatus(2), owner, AbstractSkill.SkillTarget.ENEMY_ALL, false));
  }
}
