package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class AttackStatus extends AbstractStatus {

    private static final String ID = "Attack";

    public AttackStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, amount > 0 ? StatusType.BUFF : StatusType.DEBUFF);
        setAmount(amount);
        canGoNegative = true;
    }

    @Override
    public void onApply() {
        type = amount > 0 ? StatusType.BUFF : StatusType.DEBUFF;
    }

    @Override
    public String getDesc() {
        return data.DESC_B[0] + (amount > 0 ? data.DESC_B[1] + amount + data.DESC_B[2] : data.DESC_B[3] + amount + data.DESC_B[4]);
    }

    @Override
    public int showAttack(int base) {
        return base + amount;
    }

    @Override
    public int calculateAttack(int base) {
        return base + amount;
    }
}
