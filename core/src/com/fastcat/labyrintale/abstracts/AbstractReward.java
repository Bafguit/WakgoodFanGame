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
    img = FileHandler.getUi().get("GOLD");
    this.type = type;
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
