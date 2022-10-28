package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.ManduSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class KimchiMandu extends AbstractItem {

  private static final String ID = "mandu";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public KimchiMandu() {
    super(ID, null, RARITY);
  }

  @Override
  public void onGain() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.stat.moveRes += 40;
    }
  }

  @Override
  public void onRemove() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.stat.moveRes -= 40;
    }
  }
}