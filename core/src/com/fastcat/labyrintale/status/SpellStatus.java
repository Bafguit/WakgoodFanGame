package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class SpellStatus extends AbstractStatus {

    private static final String ID = "Spell";

    public SpellStatus(int amount) {
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
    public int showSpell(int base) {
        return base + amount;
    }
}
