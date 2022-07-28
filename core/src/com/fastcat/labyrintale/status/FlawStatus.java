package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class FlawStatus extends AbstractStatus {

    private static final String ID = "Flaw";

    public FlawStatus() {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public void endOfRound() {
        bot(new ReduceStatusAction(this, 1, true));
    }

    @Override
    public float spellMultiply() {
        return 0.7f;
    }
}
