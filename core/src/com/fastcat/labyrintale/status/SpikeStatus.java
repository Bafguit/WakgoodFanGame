package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.StatusDamageAction;

public class SpikeStatus extends AbstractStatus {

    private static final String ID = "Spike";
    private static final SkillTarget TARGET = SkillTarget.NONE;

    public SpikeStatus(int amount) {
        super(ID, TARGET, StatusType.BUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public int onAttacked(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL) {
            top(new StatusDamageAction(this, attacker, AttackAction.AttackType.LIGHT, false, false, true));
        }
        return damage;
    }
}
