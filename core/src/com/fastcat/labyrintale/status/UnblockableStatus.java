package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class UnblockableStatus extends AbstractStatus {

    private static final String ID = "Unblockable";

    public UnblockableStatus() {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public void endOfRound() {
        bot(new RemoveStatusAction(this, true));
    }
}
