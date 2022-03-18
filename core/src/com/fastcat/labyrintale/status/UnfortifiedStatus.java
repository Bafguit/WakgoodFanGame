package com.fastcat.labyrintale.status;

import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import javax.swing.*;

public class UnfortifiedStatus extends AbstractStatus {

    private static final String ID = "Unfortified";
    private boolean fromEnemy;

    public UnfortifiedStatus(int amount, boolean fromEnemy) {
        super(ID, AbstractSkill.SkillTarget.NONE);
        setAmount(amount);
        this.fromEnemy = fromEnemy;
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public int onAttacked(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            return MathUtils.floor((float) d * 1.5f);
        } else return d;
    }

    @Override
    public void startOfTurn() {
        if(fromEnemy) fromEnemy = false;
        else ActionHandler.top(new ReduceStatusAction(this, 1, true));
    }
}
