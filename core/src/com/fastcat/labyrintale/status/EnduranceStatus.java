package com.fastcat.labyrintale.status;

import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class EnduranceStatus extends AbstractStatus {

    private static final String ID = "Endurance";

    public EnduranceStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public int onAttacked(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            return MathUtils.floor((float) d * 0.5f);
        } else return d;
    }

    @Override
    public void startOfTurn() {
        ActionHandler.bot(new ReduceStatusAction(this, 1, true));
    }
}
