package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.FlashAction;
import com.fastcat.labyrintale.actions.SuicideAction;

public class SelfBombStatus extends AbstractStatus {

    private static final String ID = "SelfBomb";
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

    public SelfBombStatus(int amount) {
        super(ID, TARGET, StatusType.STATIC);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    public void endOfTurn() {
        if (amount > 1) {
            amount--;
        } else {
            top(new SuicideAction(owner, 0.3f));
            top(new FlashAction(owner, img));
        }
    }
}
