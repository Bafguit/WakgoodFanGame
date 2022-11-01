package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractRoom;

public class GoldReward extends AbstractReward {

  public int gold;

  public GoldReward(int gold) {
    super(RewardType.GOLD);
    this.gold = gold;
    setInfo("골드", "골드를 &y<" + this.gold + "> 획득합니다.");
  }
  @Override
  public void takeReward() {
    AbstractLabyrinth.modifyGold(gold);
  }
}
