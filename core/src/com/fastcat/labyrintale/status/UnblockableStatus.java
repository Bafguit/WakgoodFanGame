package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class UnblockableStatus extends AbstractStatus {

    private static final String ID = "Unblockable";

    public UnblockableStatus() {
        this(1);
    }

    public UnblockableStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }
    @Override
    public void startOfTurn() {
        top(new ReduceStatusAction(this, 1, StatusType.BUFF, true));
    }
}
