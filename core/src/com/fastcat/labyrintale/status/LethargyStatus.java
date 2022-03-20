package com.fastcat.labyrintale.status;

import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class LethargyStatus extends AbstractStatus {

    private static final String ID = "Lethargy";
    private boolean fromEnemy;

    public LethargyStatus(int amount, boolean fromEnemy) {
        super(ID, AbstractSkill.SkillTarget.NONE);
        setAmount(amount);
        this.fromEnemy = fromEnemy;
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public int onAttack(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            return MathUtils.floor((float) d * 0.5f);
        } else return d;
    }

    @Override
    public void endOfTurn() {
        if(fromEnemy) fromEnemy = false;
        else ActionHandler.bot(new ReduceStatusAction(this, 1, true));
    }
}
