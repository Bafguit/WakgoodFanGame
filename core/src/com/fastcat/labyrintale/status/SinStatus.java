package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class SinStatus extends AbstractStatus {

    private static final String ID = "Sin";

    public SinStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void endOfTurn() {
        bot(new RemoveStatusAction(id, null, true));
    }

    @Override
    public int showAttack(int base) {
        return base - amount;
    }
}
