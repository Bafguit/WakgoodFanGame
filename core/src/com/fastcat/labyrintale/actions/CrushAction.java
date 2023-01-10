package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class CrushAction extends AbstractAction {

    public AbstractSkill skill;
    public AbstractEntity.DamageInfo info;
    public AbstractEntity.DamageInfo info2;
    public Array<AbstractEntity> t2 = new Array<>();

    public CrushAction(AbstractSkill s, boolean isFast) {
        super(s.owner, AbstractSkill.SkillTarget.ENEMY_FIRST, isFast ? 0.25f : 0.5f);
        info = new AbstractEntity.DamageInfo(actor, s.attack);
        info2 = new AbstractEntity.DamageInfo(actor, s.value);
        skill = s;
        t2 = AbstractSkill.getTargets(target.get(0), AbstractSkill.SkillTarget.OTHER);
    }

    public CrushAction(AbstractSkill s) {
        this(s, false);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0 || t2.size > 0) {
                AttackAction.playAttackSfx(AttackAction.AttackType.SMASH);
                if (actor != null) {
                    actor.animation.setAndIdle("attack");
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if (te.isAlive()) {
                        te.takeDamage(info);
                    }
                }
                for (int i = 0; i < t2.size; i++) {
                    AbstractEntity te = t2.get(i);
                    if (te.isAlive()) {
                        te.takeDamage(info2);
                    }
                }
            } else isDone = true;
        }
    }
}
