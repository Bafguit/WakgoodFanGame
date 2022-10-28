package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.HikiSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class HikiKing extends AbstractItem {

  private static final String ID = "hiki";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public HikiKing() {
    super(ID, null, RARITY);
  }

  @Override
  public void onGain() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.stat.attack++;
    }
  }

  @Override
  public void onRemove() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.stat.attack--;
    }
  }
}
