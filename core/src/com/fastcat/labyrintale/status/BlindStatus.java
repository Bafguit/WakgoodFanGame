package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

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
    public void onInitial() {
        owner.badLuck++;
    }

    @Override
    public void onRemove() {
        owner.badLuck--;
    }

    @Override
    public void endOfTurn() {
        if (isSelf) isSelf = false;
        else top(new RemoveStatusAction(this, true));
    }

    @Override
    public float attackMultiply() {
        return 0.3f;
    }
}
