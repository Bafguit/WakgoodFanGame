package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;

public class ProvokeStatus extends AbstractStatus {

  private static final String ID = "Provoke";

  public ProvokeStatus(AbstractEntity owner) {
    this(owner, 1);
  }

  public ProvokeStatus(AbstractEntity owner, int amount) {
    super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
    this.owner = owner;
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void endOfTurn() {
    if (isSelf) isSelf = false;
    else top(new ReduceStatusAction(this, 1, StatusType.STATIC, true));
  }
}
