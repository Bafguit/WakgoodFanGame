package com.fastcat.labyrintale.interfaces;

import com.fastcat.labyrintale.rewards.HealReward;

public interface GetSelectedExp {
  void expSelected(HealReward.HealType type);
}
