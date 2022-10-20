package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class FabricMail extends AbstractItem {

  private static final String ID = "FabricMail";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public FabricMail(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(
        new ApplyStatusAction(
            new EnduranceStatus(1), owner, AbstractSkill.SkillTarget.SELF, false));
  }
}
