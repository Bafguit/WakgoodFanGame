package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class LowHealAction extends AbstractAction {

    public int heal;

    public LowHealAction(AbstractEntity actor, int heal, boolean isFast) {
        super(actor, isFast ? 0.25f : 0.5f);
        this.heal = heal;
    }

    public LowHealAction(int heal) {
        super(null, 0.5f);
        this.heal = heal;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            Array<AbstractPlayer> temp = new Array<>();
            int low = 2147483647;
            for (int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if (p.isAlive() && p.health < low) low = p.health;
            }
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (p.health == low) temp.add(p);
            }
            if(temp.size > 0) {
                for (int i = 0; i < temp.size; i++) {
                    AbstractEntity te = temp.get(i);
                    te.heal(actor != null ? actor.calculateSpell(heal) : heal);
                }
            }
        }
    }
}
