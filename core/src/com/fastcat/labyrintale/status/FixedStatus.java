package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class FixedStatus extends AbstractStatus {

    private static final String ID = "Fixed";

    public FixedStatus() {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.STATIC);
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
    public void endOfRound() {
        bot(new RemoveStatusAction(this, true));
    }

    @Override
    public void onRemove() {
        owner.movable--;
    }
}
