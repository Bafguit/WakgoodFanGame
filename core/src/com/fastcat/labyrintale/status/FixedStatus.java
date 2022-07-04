package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class FixedStatus extends AbstractStatus {

    private static final String ID = "Fixed";

    public FixedStatus(AbstractEntity owner) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
        this.owner = owner;
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public void onApply() {
        owner.movable++;
    }

    @Override
    public void onRemove() {
        owner.movable--;
    }
}
