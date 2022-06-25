package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class ImmuneStatus extends AbstractStatus {

    private static final String ID = "Immune";

    public ImmuneStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public float onAttackedMultiply(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            flash();
            ActionHandler.top(new ReduceStatusAction(this, 1, true));
            return 0;
        } else return 1.0f;
    }
}
