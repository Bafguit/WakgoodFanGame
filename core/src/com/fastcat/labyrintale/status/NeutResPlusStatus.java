package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;

public class NeutResPlusStatus extends AbstractStatus {

  private static final String ID = "NeutResPlus";

  public NeutResPlusStatus(int amount) {
    super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onInitial() {
    owner.stat.neutRes += 40;
  }

  @Override
  public void startOfTurn() {
    if (notSelf) notSelf = false;
    else top(new ReduceStatusAction(this, 1, StatusType.DEBUFF, true));
  }

  @Override
  public void onRemove() {
    owner.stat.neutRes -= 40;
  }
}
