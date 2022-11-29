package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class SpeedMinusStatus extends AbstractStatus {

  private static final String ID = "SpeedMinus";

  public SpeedMinusStatus(int amount) {
    super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onInitial() {
    owner.stat.speed -= 4;
  }

  @Override
  public void startOfRound() {
    if(amount > 1) amount--;
    else top(new RemoveStatusAction(this, true));
  }

  @Override
  public void onRemove() {
    owner.stat.speed += 4;
  }
}
