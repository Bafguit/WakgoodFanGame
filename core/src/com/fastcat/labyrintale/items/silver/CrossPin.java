package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ImmuneStatus;
import com.fastcat.labyrintale.status.LethargyStatus;
import com.fastcat.labyrintale.status.ProvokeStatus;

public class CrossPin extends AbstractItem {

  private static final String ID = "CrossPin";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public CrossPin(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.debuRes += 10;
    owner.stat.moveRes += 10;
  }

  @Override
  public void onRemove() {
    owner.stat.debuRes -= 10;
    owner.stat.moveRes -= 10;
  }

  @Override
  public void atBattleStart() {
    flash();
    top(new ApplyStatusAction(new ProvokeStatus(owner), owner, owner, true));
  }
}
