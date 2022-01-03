package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;

public class ApplyStatusAction extends AbstractAction {

    private AbstractStatus status;

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, AbstractSkill.CardTarget target, boolean fast) {
        super(actor, target, fast ? 0.25f : DUR_DEFAULT);
        this.status = status;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            boolean done = false;
            for(int i = 0; i < 5; i++) {
                AbstractStatus temp = actor.status[i];
                if(temp.id.equals(status.id))  {
                    temp.amount += status.amount;
                    if(temp.amount < 0 && !temp.canGoNegative) {
                        temp.onRemove();
                        if(i < 4) System.arraycopy(actor.status, i + 1, actor.status, 0, 4 - i);
                        actor.status[4] = null;
                    } else temp.flash();
                    done = true;
                    break;
                }
            }
            if(!done) {
                for (int i = 0; i < 5; i++) {
                    AbstractStatus temp = actor.status[i];
                    if (temp == null) {
                        temp = status;
                        temp.flash();
                        done = true;
                        break;
                    }
                }
            }
            if(!done) {
                actor.status[0].onRemove();
                System.arraycopy(actor.status, 1, actor.status, 0, 4);
                actor.status[4] = status;
                status.flash();
            }
        }
    }
}
