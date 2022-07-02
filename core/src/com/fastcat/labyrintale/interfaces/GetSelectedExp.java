package com.fastcat.labyrintale.interfaces;

import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.rewards.ExpReward;

public interface GetSelectedExp {
    void expSelected(ExpReward.ExpType type);
}
