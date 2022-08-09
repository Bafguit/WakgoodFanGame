package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class HealReward extends AbstractReward {

    public int amount;

    public HealReward(int amount) {
        super(RewardType.HEAL);
        this.amount = amount;
        setInfo("회복", "생존해 있는 모든 동료의 체력을 &g<" + this.amount + "> 회복합니다.");
    }

    @Override
    public void takeReward() {
        SoundHandler.playSfx("HEAL");
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            if (p.isAlive()) p.heal(amount);
        }
    }
}
