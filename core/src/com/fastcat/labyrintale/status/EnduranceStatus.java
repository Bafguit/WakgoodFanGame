package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class EnduranceStatus extends AbstractStatus {

  private static final String ID = "Endurance";

  public EnduranceStatus(int amount) {
    super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public int onDamaged(AbstractEntity t, int d, AbstractEntity.DamageType type) {
    if (type == AbstractEntity.DamageType.NORMAL) {
      flash();
      top(new RemoveStatusAction(this, true));
      return d - amount;
    } else return d;
  }
}
