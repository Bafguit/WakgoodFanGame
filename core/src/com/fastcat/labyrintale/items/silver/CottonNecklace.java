package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class CottonNecklace extends AbstractItem {

  private static final String ID = "CottonNecklace";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public CottonNecklace(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(5);
    owner.stat.neutRes += 5;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
    owner.stat.neutRes -= 5;
  }

  @Override
  public void atBattleStart() {
    flash();
    top(
        new ApplyStatusAction(
            new EnduranceStatus(3), owner, AbstractSkill.SkillTarget.PLAYER_ALL, false));
  }
}
