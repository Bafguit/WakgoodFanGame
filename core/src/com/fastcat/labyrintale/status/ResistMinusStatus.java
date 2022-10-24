package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;

public class ResistMinusStatus extends AbstractStatus {

  private static final String ID = "ResistMinus";

  public ResistMinusStatus(int amount) {
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
    owner.stat.neutRes -= 40;
    owner.stat.debuRes -= 40;
  }

  @Override
  public void startOfTurn() {
    if (notSelf) notSelf = false;
    else top(new ReduceStatusAction(this, 1, StatusType.BUFF, true));
  }

  @Override
  public void onRemove() {
    owner.stat.moveRes += 40;
    owner.stat.neutRes += 40;
    owner.stat.debuRes += 40;
  }
}
