package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class ProvokeStatus extends AbstractStatus {

    private static final String ID = "Provoke";

    public ProvokeStatus(AbstractEntity owner) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
        this.owner = owner;
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public void startOfTurn() {
        bot(new RemoveStatusAction(this, true));
    }
}
