package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.UpgradeAction;
import com.fastcat.labyrintale.skills.player.advisor.DuksuSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Duksu extends AbstractItem {

  private static final String ID = "duksu";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public Duksu() {
    super(ID, null, RARITY);
  }

  public void atBattleStart() {
    AbstractSkill[] temp = new AbstractSkill[4];
    for(int i = 0; i < 4; i++) {
      AbstractPlayer p = AbstractLabyrinth.players[i];
      temp[i] = p.hand[AbstractLabyrinth.publicRandom.random(0, 2)];
    }
    bot(new UpgradeAction(temp));
  }
}
