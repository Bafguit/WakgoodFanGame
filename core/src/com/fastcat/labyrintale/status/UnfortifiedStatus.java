package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class UnfortifiedStatus extends AbstractStatus {

    private static final String ID = "Unfortified";

    public UnfortifiedStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public float onAttackedMultiply(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL) {
            return 1.5f;
        } else return 1.0f;
    }

    @Override
    public void endOfTurn() {
        if (isSelf) isSelf = false;
        else if (amount > 1) amount--;
        else top(new RemoveStatusAction(this, true));
    }

    @Override
    public float attackedMultiply() {
        return 1.5f;
    }
}
