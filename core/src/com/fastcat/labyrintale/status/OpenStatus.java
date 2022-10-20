package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.StatusDamageAction;

public class OpenStatus extends AbstractStatus {

    private static final String ID = "Open";
    private static final SkillTarget TARGET = SkillTarget.SELF;

    public OpenStatus(int amount) {
        super(ID, TARGET, StatusType.STATIC);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public int onDamaged(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL) {
            top(new ApplyStatusAction(new ScarStatus(amount), owner, owner, true));
        }
        return d;
    }
}
