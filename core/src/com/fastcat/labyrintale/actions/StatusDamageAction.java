package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.NONE;

public class StatusDamageAction extends AbstractAction {

    public AbstractStatus status;
    public boolean reduce = false;
    public boolean remove = false;

    public StatusDamageAction(AbstractStatus s) {
        super(s.owner, 0.5f);
        status = s;
    }

    public StatusDamageAction(AbstractStatus s, boolean reduce, boolean remove) {
        this(s);
        this.reduce = reduce;
        this.remove = remove;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            status.flash(actor);
            Array<AbstractEntity> temp = AbstractSkill.getTargets(status);
            for(AbstractEntity e : temp) {
                e.takeDamage(new AbstractEntity.DamageInfo(actor, status.amount, AbstractEntity.DamageType.SPIKE));
            }
            if (reduce) actor.applyStatus(status, -1);
            if (remove) actor.removeStatus(status.id);
        }
    }
}
