package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.StatusDamageAction;

public class ShockStatus extends AbstractStatus {

    private static final String ID = "Shock";
    private static final SkillTarget TARGET = SkillTarget.S_B;

    public ShockStatus(int amount) {
        super(ID, TARGET, StatusType.DEBUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void onDamaged(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            top(new StatusDamageAction(this, AttackAction.AttackType.BURN, false, true));
        }
    }
}
