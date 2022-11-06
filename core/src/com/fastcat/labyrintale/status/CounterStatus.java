package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class CounterStatus extends AbstractStatus {

  private static final String ID = "Counter";
  private static final SkillTarget TARGET = SkillTarget.NONE;
  private int realAmount;

  public CounterStatus(int amount) {
    super(ID, TARGET, StatusType.BUFF);
    realAmount = amount;
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onApply(int amount) {
    realAmount += amount;
  }

  @Override
  public void startOfTurn() {
    top(new RemoveStatusAction(this, true));
  }

  @Override
  public int onAttacked(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
    if (type == AbstractEntity.DamageType.NORMAL) {
      top(
          new AttackAction(
              owner,
              attacker,
              amount,
              AbstractEntity.DamageType.SPIKE,
              AttackAction.AttackType.LIGHT,
              true));
    }
    return damage;
  }
}
