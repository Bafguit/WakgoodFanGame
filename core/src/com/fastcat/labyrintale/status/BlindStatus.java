package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class BlindStatus extends AbstractStatus {

    private static final String ID = "Blind";

    public BlindStatus() {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void onAttack(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        flash();
        ActionHandler.top(new RemoveStatusAction(this, true));
    }
    @Override
    public void endOfTurn() {
        top(new RemoveStatusAction(this, true));
    }

    @Override
    public float attackMultiply() {
        return 0;
    }
}
