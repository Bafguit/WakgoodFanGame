package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.ReduceStatusAction;

public class RegenStatus extends AbstractStatus {

    private static final String ID = "Regen";
    private static final SkillTarget TARGET = SkillTarget.SELF;

    public RegenStatus(int amount) {
        super(ID, TARGET, StatusType.BUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void endOfTurn() {
        top(new ReduceStatusAction(this, 1, StatusType.BUFF, true));
        top(new HealAction(null, owner, amount));
    }

    @Override
    public int onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL) {
            top(new ReduceStatusAction(this, 1, StatusType.BUFF, true));
        }
        return damage;
    }
}
