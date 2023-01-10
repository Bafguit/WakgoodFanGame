package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;

public class CatalystAction extends AbstractAction {

    private final int multiply;

    public CatalystAction(AbstractEntity actor, AbstractSkill.SkillTarget t, int multiply, boolean isFast) {
        super(actor, t, isFast ? 0.25f : 0.5f);
        this.multiply = multiply;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                AttackAction.playAttackSfx(AttackAction.AttackType.INFECTION);
                for (AbstractEntity t : target) {
                    EffectHandler.add(new HitEffect(t, AttackAction.getEffectImg(AttackAction.AttackType.INFECTION)));
                    for(AbstractStatus s : t.status) {
                        if(s.type == AbstractStatus.StatusType.DEBUFF && s.hasAmount) s.amount *= multiply;
                    }
                }
            } else isDone = true;
        }
    }
}
