package com.fastcat.labyrintale.status;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

public class BlindStatus extends AbstractStatus {

    private static final String ID = "Blind";

    public BlindStatus() {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.DEBUFF);
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public int onAttack(AbstractEntity t, int d, AbstractEntity.DamageType type) {
        if(type == AbstractEntity.DamageType.NORMAL) {
            flash();
            ActionHandler.top(new RemoveStatusAction(this, true));
            return 0;
        } else return d;
    }
}
