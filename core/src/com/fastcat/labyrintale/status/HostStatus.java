package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ApplyStatusAction;

public class HostStatus extends AbstractStatus {

    private static final String ID = "Host";
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

    public HostStatus(int amount) {
        super(ID, TARGET, StatusType.STATIC);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    public void onDeath(AbstractEntity murder) {
        flash();
        top(new ApplyStatusAction(new UnfortifiedStatus(amount), null, target, true));
    }
}
