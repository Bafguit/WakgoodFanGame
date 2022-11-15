package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;

public class MoveResMinusStatus extends AbstractStatus {

  private static final String ID = "MoveResMinus";

  public MoveResMinusStatus(int amount) {
    super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onInitial() {
    owner.stat.moveRes -= 40;
  }

  @Override
  public void endOfTurn() {
    if (isSelf) isSelf = false;
    else top(new ReduceStatusAction(this, 1, StatusType.BUFF, true));
  }

  @Override
  public void onRemove() {
    owner.stat.moveRes += 40;
  }
}
