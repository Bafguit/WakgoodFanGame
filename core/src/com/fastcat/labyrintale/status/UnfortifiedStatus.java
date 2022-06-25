package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class UnfortifiedStatus extends AbstractStatus {

    private static final String ID = "Unfortified";
    private boolean fromEnemy;

    public UnfortifiedStatus(int amount, boolean fromEnemy) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
        setAmount(amount);
        this.fromEnemy = fromEnemy;
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public float onAttackedMultiply(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            return 1.5f;
        } else return 1.0f;
    }

    @Override
    public void endOfTurn() {
        if(fromEnemy) fromEnemy = false;
        else ActionHandler.bot(new ReduceStatusAction(this, 1, true));
    }
}
