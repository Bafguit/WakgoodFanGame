package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class CourageStatus extends AbstractStatus {

    private static final String ID = "Courage";

    public CourageStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public int onAttack(AbstractEntity e, int dmg, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            flash();
            ActionHandler.top(new RemoveStatusAction(this, true));
            return dmg + amount;
        } else return dmg;
    }

    @Override
    public int showAttack(int base) {
        return base + amount;
    }

    @Override
    public void endOfTurn() {
        ActionHandler.bot(new RemoveStatusAction(this, true));
    }
}
