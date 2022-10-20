package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ApplyStatusAction;

public class PunishStatus extends AbstractStatus {

  private static final String ID = "Punish";
  private static final SkillTarget TARGET = SkillTarget.NONE;

  public PunishStatus(int amount) {
    super(ID, TARGET, StatusType.STATIC);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
    top(new ApplyStatusAction(new SinStatus(amount), owner, target, true));
  }
}
