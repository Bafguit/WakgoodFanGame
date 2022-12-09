package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.MoveAction;

public class LureStatus extends AbstractStatus {

    private static final String ID = "Lure";
    private static final SkillTarget TARGET = SkillTarget.NONE;

    public LureStatus() {
        super(ID, TARGET, StatusType.STATIC);
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public int onDamaged(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL) {
            flash();
            top(new MoveAction(t, owner, 0, 0.2f));
        }
        return d;
    }
}
