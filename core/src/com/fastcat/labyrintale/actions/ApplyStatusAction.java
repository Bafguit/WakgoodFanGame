package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;

import java.util.Objects;

public class ApplyStatusAction extends AbstractAction {

    private final AbstractStatus status;
    private final Array<AbstractEntity> target;

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, AbstractSkill.CardTarget target, boolean fast) {
        super(actor, target, fast ? 0.25f : DUR_DEFAULT);
        this.status = status;
        this.target = AbstractSkill.getTargets(target);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            for(AbstractEntity e : target) {
                e.applyStatus(status, status.amount);
            }
        }
    }
}
