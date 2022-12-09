package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class LethargyStatus extends AbstractStatus {

    private static final String ID = "Lethargy";

    public LethargyStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void endOfTurn() {
        if (isSelf) isSelf = false;
        else if (amount > 1) amount--;
        else top(new RemoveStatusAction(this, true));
    }

    @Override
    public float attackMultiply() {
        return 0.7f;
    }
}
