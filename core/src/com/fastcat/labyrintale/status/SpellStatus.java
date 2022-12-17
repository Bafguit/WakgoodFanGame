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
    public void onApply(int amount) {
        owner.stat.spell += amount;
        type = this.amount > 0 ? StatusType.BUFF : StatusType.DEBUFF;
    }

    @Override
    public void onRemove() {
        owner.stat.spell -= amount;
    }

    @Override
    public String getDesc() {
        return exDesc[0] + (amount > 0 ? exDesc[1] + amount + exDesc[2] : exDesc[3] + -amount + exDesc[4]);
    }
}
