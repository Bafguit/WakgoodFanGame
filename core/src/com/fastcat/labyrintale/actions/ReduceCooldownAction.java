package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class ReduceCooldownAction extends AbstractAction {

    private final int amount;

    public ReduceCooldownAction(Array<AbstractEntity> e, int amount) {
        super(null, 0.5f);
        target = e;
        this.amount = amount;
    }

    public ReduceCooldownAction(AbstractEntity e, int amount) {
        super(null, 0.5f);
        target = new Array<>();
        target.add(e);
        this.amount = amount;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            for(AbstractEntity e : target) {
                for(AbstractSkill s : e.hand) {
                    s.cooldown = Math.min(Math.max(s.cooldown - amount, 0), s.cooldown);
                }
            }
        }
    }
}
