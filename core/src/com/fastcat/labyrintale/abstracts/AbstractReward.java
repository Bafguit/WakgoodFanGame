package com.fastcat.labyrintale.abstracts;

public class AbstractReward {

    public RewardType type;
    public boolean isDone = false;
    public int gold;

    public AbstractReward(RewardType type, int gold) {
        this.type = type;
        this.gold = gold;
    }

    public enum RewardType {
        SKILL, GOLD, TALENT
    }
}
