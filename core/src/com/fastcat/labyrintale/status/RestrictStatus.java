package com.fastcat.labyrintale.status;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.SELF;

import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.StatusDamageAction;

public class RestrictStatus extends AbstractStatus {

  private static final String ID = "Restrict";

  public RestrictStatus(int amount) {
    super(ID, SELF, StatusType.DEBUFF);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void endOfTurn() {
    top(new StatusDamageAction(this, AttackAction.AttackType.NONE, false, false));
  }
}
