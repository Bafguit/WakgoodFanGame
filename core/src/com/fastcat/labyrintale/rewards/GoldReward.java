package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractRoom;

public class GoldReward extends AbstractReward {

    public int gold;

    public GoldReward(AbstractRoom.RoomType monsterType) {
        this(getAmountByMonster(monsterType));
    }

    public GoldReward(int gold) {
        super(RewardType.GOLD);
        this.gold = gold;
        setInfo("골드", "골드를 &y<" + this.gold + "> 획득합니다.");
    }

    private static int getAmountByMonster(AbstractRoom.RoomType type) {
        switch (type) {
            case BOSS:
                return calculate(115);
            case ELITE:
                return calculate(75);
            default:
                return calculate(35);
        }
    }

    private static int calculate(int g) {
        if (AbstractLabyrinth.restriction.FAM == 1) g *= 0.9f;
        else if (AbstractLabyrinth.restriction.FAM == 2) g *= 0.7f;
        else if (AbstractLabyrinth.restriction.FAM == 3) g *= 0.5f;
        if (AbstractLabyrinth.advisor.cls == AbstractAdvisor.AdvisorClass.SOPHIA) g *= 1.2f;
        return g + AbstractLabyrinth.publicRandom.random(-5, 5);
    }

    @Override
    public void takeReward() {
        AbstractLabyrinth.gold += gold;
    }
}
