package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class NeutStatus extends AbstractStatus {

    private static final String ID = "Neut";

    public NeutStatus(AbstractEntity owner) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.STATIC);
        this.owner = owner;
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public void onInitial() {
        owner.isNeut = true;
        owner.stat.critical -= 10;
        owner.stat.speed -= 3;
    }

    @Override
    public void onRemove() {
        owner.isNeut = false;
        owner.stat.critical += 10;
        owner.stat.speed += 3;
    }
}
