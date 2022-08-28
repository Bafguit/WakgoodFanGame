package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class NeutResStatus extends AbstractStatus {

    private static final String ID = "NeutRes";

    public NeutResStatus(AbstractEntity owner, int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.STATIC);
        this.owner = owner;
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void onApply(int amt) {
        if(owner.stat.neutRes < amt) {
            owner.stat.neutRes = 0;
        } else {
            owner.stat.neutRes -= amt;
        }
    }

    @Override
    public void onRemove() {
        owner.stat.neutRes += amount;
    }
}
