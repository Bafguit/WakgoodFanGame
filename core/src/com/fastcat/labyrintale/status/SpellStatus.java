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
        return exDesc[0] + (amount > 0 ? exDesc[1] + amount + exDesc[2] : exDesc[3] + amount + exDesc[4]);
    }

    @Override
    public int showSpell(int base) {
        return base + amount;
    }
}
