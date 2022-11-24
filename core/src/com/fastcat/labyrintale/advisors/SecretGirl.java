package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.SecretSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class SecretGirl extends AbstractItem {

  private static final String ID = "secret";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public SecretGirl() {
    super(ID, null, RARITY);
  }

  @Override
  public void onGain() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.goodLuck++;
    }
  }

  @Override
  public void onRemove() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.goodLuck--;
    }
  }
}