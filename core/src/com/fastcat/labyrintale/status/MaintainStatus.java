package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class MaintainStatus extends AbstractStatus {

  private static final String ID = "Maintain";

  public MaintainStatus(AbstractEntity owner) {
    super(ID, AbstractSkill.SkillTarget.NONE, StatusType.STATIC);
    this.owner = owner;
  }

  @Override
  public String getDesc() {
    return exDesc[0];
  }
}
