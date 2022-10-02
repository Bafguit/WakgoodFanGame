package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class FixedStatus extends AbstractStatus {

    private static final String ID = "Fixed";
    private final boolean fromEnemy;

    public FixedStatus(boolean fromEnemy) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.STATIC);
        this.fromEnemy = fromEnemy;
    }

    @Override
    public String getDesc() {
        return exDesc[0];
    }

    @Override
    public void onInitial() {
        owner.movable++;
    }

    @Override
    public void endOfTurn() {
        if(fromEnemy) top(new RemoveStatusAction(this, true));
    }

    @Override
    public void startOfRound() {
        if(!fromEnemy) top(new RemoveStatusAction(this, true));
    }

    @Override
    public void onRemove() {
        owner.movable--;
    }
}
