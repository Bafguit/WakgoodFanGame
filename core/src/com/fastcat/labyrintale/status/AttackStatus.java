package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class AttackStatus extends AbstractStatus {

  private static final String ID = "Attack";

  public AttackStatus(int amount) {
    super(ID, AbstractSkill.SkillTarget.NONE, amount > 0 ? StatusType.BUFF : StatusType.DEBUFF);
    setAmount(amount);
    canGoNegative = true;
  }

  @Override
  public void onApply(int amount) {
    owner.stat.attack += amount;
    type = this.amount > 0 ? StatusType.BUFF : StatusType.DEBUFF;
  }

  @Override
  public void onRemove() {
    owner.stat.attack -= amount;
  }

  @Override
  public String getDesc() {
    return exDesc[0]
        + (amount > 0 ? exDesc[1] + amount + exDesc[2] : exDesc[3] + amount + exDesc[4]);
  }
}
