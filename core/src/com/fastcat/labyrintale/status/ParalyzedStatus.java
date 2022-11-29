package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class ParalyzedStatus extends AbstractStatus {

  private static final String ID = "Paralyzed";

  public ParalyzedStatus(int amount) {
    super(ID, SkillTarget.NONE, StatusType.DEBUFF);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onInitial() {
    owner.stat.speed -= 2;
    owner.stat.critical -= 20;
  }

  @Override
  public void onRemove() {
    owner.stat.speed += 2;
    owner.stat.debuRes += 20;
  }

  @Override
  public void endOfTurn() {
    if (isSelf) isSelf = false;
    else if(amount > 1) amount--;
    else top(new RemoveStatusAction(this, true));
  }
}
