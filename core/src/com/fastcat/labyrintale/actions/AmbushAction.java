package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class AmbushAction extends AbstractAction {

    public AbstractSkill skill;
    public AbstractEntity.DamageInfo info;

    public AmbushAction(AbstractSkill s, boolean isFast) {
        super(s.owner, s.target, isFast ? 0.25f : 0.5f);
        info = new AbstractEntity.DamageInfo(actor, s.attack);
        skill = s;
    }

    public AmbushAction(AbstractSkill s) {
        this(s, false);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            AttackAction.playAttackSfx(
                    skill.upgradeCount > 3 ? AttackAction.AttackType.HEAVY : AttackAction.AttackType.LIGHT);
            if (target.size > 0) {
                if (actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
                    actor.state.addAnimation(0, "idle", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if (te.isAlive()) {
                        te.takeDamage(info);
                    }
                }
                skill.upgrade();
            } else isDone = true;
        }
    }
}
