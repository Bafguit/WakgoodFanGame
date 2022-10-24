package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.LethargyStatus;

public class CrossPin extends AbstractItem {

  private static final String ID = "CrossPin";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public CrossPin(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(5);
    owner.stat.debuRes += 5;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
    owner.stat.debuRes -= 5;
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(
        new ApplyStatusAction(
            new LethargyStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_ALL, false));
  }
}
