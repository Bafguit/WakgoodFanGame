package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class NeutResStatus extends AbstractStatus {

  private static final String ID = "NeutRes";

  public NeutResStatus(int amount) {
    super(ID, AbstractSkill.SkillTarget.NONE, StatusType.STATIC);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onApply(int amount) {
    owner.stat.neutRes -= amount;
  }

  @Override
  public void onRemove() {
    owner.stat.neutRes += amount;
  }
}
