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
                boolean done = false;
                AbstractStatus s = status.cpy();
                Objects.requireNonNull(s).owner = e;
                for (int i = 0; i < 5; i++) {
                    AbstractStatus temp = e.status[i];
                    if (temp != null && temp.id.equals(s.id)) {
                        if(temp.hasAmount) {
                            temp.amount += s.amount;
                            if (temp.amount < 0 && !temp.canGoNegative) {
                                temp.onRemove();
                                if (i < 4) System.arraycopy(e.status, i + 1, e.status, 0, 4 - i);
                                e.status[4] = null;
                            }
                        }
                        temp.onApply();
                        temp.flash(e);
                        done = true;
                        break;
                    }
                }
                if(!done) {
                    for (int i = 0; i < 5; i++) {
                        if (e.status[i] == null) {
                            e.status[i] = s;
                            s.onApply();
                            s.flash(e);
                            done = true;
                            break;
                        }
                    }
                }
                if(!done) {
                    e.status[0].onRemove();
                    System.arraycopy(e.status, 1, e.status, 0, 4);
                    e.status[4] = s;
                    s.onApply();
                    s.flash(e);
                }
            }
        }
    }
}
