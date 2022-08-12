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
        top(new StatusDamageAction(this, AttackAction.AttackType.BURN, false, false, false));
    }

    public void endOfTurn() {
        if(amount > 1) {
            top(new ReduceStatusAction(this, 1, true));
        } else {
            top(new DieAction(owner, 0.3f));
            top(new FlashAction(owner, img));
        }
    }
}
