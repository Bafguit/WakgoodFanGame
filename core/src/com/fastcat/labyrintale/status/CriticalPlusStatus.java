package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class CriticalPlusStatus extends AbstractStatus {

    private static final String ID = "CriticalPlus";

    public CriticalPlusStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void onInitial() {
        owner.stat.critical += 30;
    }

    @Override
    public void endOfTurn() {
        if (isSelf) isSelf = false;
        else if (amount > 1) amount--;
        else top(new RemoveStatusAction(this, true));
    }

    @Override
    public void onRemove() {
        owner.stat.critical -= 30;
    }
}
