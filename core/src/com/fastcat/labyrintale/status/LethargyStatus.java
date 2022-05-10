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
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
        setAmount(amount);
        this.fromEnemy = fromEnemy;
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public float onAttackMultiply(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            return 0.7f;
        } else return 1.0f;
    }

    @Override
    public void endOfTurn() {
        if(fromEnemy) fromEnemy = false;
        else ActionHandler.bot(new ReduceStatusAction(this, 1, true));
    }

    @Override
    public float showAttackMultiply(int base) {
        return 0.7f;
    }
}
