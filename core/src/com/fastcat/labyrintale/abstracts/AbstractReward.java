package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.handlers.FileHandler;

public abstract class AbstractReward {

    public Sprite img;
    public String desc = "";
    public RewardType type;
    public boolean isDone = false;

    public AbstractReward(RewardType type) {
        img = getImg(type);
        this.type = type;
    }

    private static Sprite getImg(AbstractReward.RewardType type) {
        switch (type) {
            case HEAL:
                return FileHandler.SKILL_HEAL; //TODO 이미지 변경
            case GOLD:
                return FileHandler.GOLD;
            default:
                return FileHandler.REWARD_CARD;
        }
    }

    public abstract void takeReward();

    public enum RewardType {
        SKILL, GOLD, HEAL, TALENT
    }
}
