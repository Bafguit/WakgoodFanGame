package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.effects.UpDamageEffect;
import com.fastcat.labyrintale.handlers.*;

import static com.badlogic.gdx.graphics.Color.CHARTREUSE;

public class StartAdvisorAction extends AbstractAction {

    public int heal;

    public StartAdvisorAction(int heal) {
        super(null, 0.5f);
        this.heal = heal;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            Array<AbstractPlayer> temp = new Array<>();
            int low = 10000000;
            for(int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if(p.isAlive() && p.health < low) low = p.health;
            }
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                if(p.health == low) temp.add(p);
            }
            for (int i = 0; i < temp.size; i++) {
                AbstractEntity te = temp.get(i);
                te.heal(heal);
            }
        }
    }
}
