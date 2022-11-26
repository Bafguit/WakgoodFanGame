package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.*;

public class SelfBombStatus extends AbstractStatus {

  private static final String ID = "SelfBomb";
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

  public SelfBombStatus(int amount) {
    super(ID, TARGET, StatusType.STATIC);
    setAmount(amount);
  }

  @Override
  public String getDesc() {
    return exDesc[0] + amount + exDesc[1];
  }

  @Override
  public void onDeath(AbstractEntity m) {
    hasAmount = false;
    top(
            new AttackAction(
                    null,
                    SkillTarget.PLAYER_ALL,
                    10,
                    AbstractEntity.DamageType.SPIKE,
                    AttackAction.AttackType.BURN,
                    true));
  }

  public void endOfTurn() {
    if (amount > 1) {
      amount--;
    } else {
      top(new SuicideAction(owner, 0.3f));
      top(new FlashAction(owner, img));
    }
  }
}
