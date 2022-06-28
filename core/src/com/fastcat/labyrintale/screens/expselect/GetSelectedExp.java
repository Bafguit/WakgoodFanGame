package com.fastcat.labyrintale.screens.expselect;

import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.rewards.ExpReward;

public interface GetSelectedExp {
    void expSelected(ExpReward.ExpType type);
}
