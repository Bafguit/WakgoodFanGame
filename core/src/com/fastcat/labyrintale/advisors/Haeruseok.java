package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.RuseokSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Haeruseok extends AbstractItem {

  private static final String ID = "ruseok";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public Haeruseok() {
    super(ID, null, RARITY);
  }
}
