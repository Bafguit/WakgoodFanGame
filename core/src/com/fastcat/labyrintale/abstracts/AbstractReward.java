package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;

public abstract class AbstractReward implements GetRewardDone {

  public Sprite img;
  public String name;
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
        return FileHandler.getUi().get("HEAL");
      case GOLD:
        return FileHandler.getUi().get("GOLD");
      default:
        return FileHandler.getUi().get("REWARD_CARD");
    }
  }

  public void setInfo(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }

  public abstract void takeReward();

  public final void isRewardDone(boolean isDone) {
    this.isDone = isDone;
  }

  public enum RewardType {
    SKILL,
    EXP,
    STAT,
    GOLD,
    HEAL,
    ITEM
  }
}
