package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.CallyCarlySkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class CallyCarlyDavidson extends AbstractItem {

  private static final String ID = "callycarly";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public CallyCarlyDavidson() {
    super(ID, null, RARITY);
  }
}
