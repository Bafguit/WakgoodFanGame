package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.SophiaSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Sophia extends AbstractItem {

  private static final String ID = "sophia";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public Sophia() {
    super(ID, null, RARITY);
  }
}