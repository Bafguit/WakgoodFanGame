package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class FixedStatus extends AbstractStatus {

    private static final String ID = "Fixed";
    

    public FixedStatus() {
        this(1);
    }

    public FixedStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.STATIC);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void onInitial() {
        owner.movable++;
    }

    @Override
    public void startOfTurn() {
        if(notSelf) notSelf = false;
        else top(new ReduceStatusAction(this, 1, StatusType.STATIC, true));
    }

    @Override
    public void onRemove() {
        owner.movable--;
    }
}
