package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class Brainize extends AbstractItem {

  private static final String ID = "Brainize";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public Brainize(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.debuRes += 0.1f;
    owner.stat.neutRes += 0.1f;
    owner.stat.moveRes += 0.1f;
  }

  @Override
  public void onRemove() {
    owner.stat.debuRes -= 0.1f;
    owner.stat.neutRes -= 0.1f;
    owner.stat.moveRes -= 0.1f;
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(
        new ApplyStatusAction(
            new ImmuneStatus(1), owner, AbstractSkill.SkillTarget.PLAYER_ALL, true));
  }
}
