package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class CriticalStatus extends AbstractStatus {

    private static final String ID = "Critical";

    public CriticalStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, amount > 0 ? StatusType.BUFF : StatusType.DEBUFF);
        setAmount(amount);
        canGoNegative = true;
    }

    @Override
    public void onApply(int amount) {
        owner.stat.critical += amount;
        type = this.amount > 0 ? StatusType.BUFF : StatusType.DEBUFF;
    }

    @Override
    public void onRemove() {
        owner.stat.critical -= amount;
    }

    @Override
    public String getDesc() {
        return exDesc[0] + (amount > 0 ? exDesc[1] : exDesc[2]) + amount + exDesc[3];
    }
}
