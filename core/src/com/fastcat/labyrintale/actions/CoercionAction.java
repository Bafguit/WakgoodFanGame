package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.status.BlindStatus;
import com.fastcat.labyrintale.status.UnblockableStatus;

public class CoercionAction extends AbstractAction {

    private final boolean isBlind;

    public CoercionAction(AbstractEntity actor, boolean isBlind) {
        super(actor, AbstractSkill.SkillTarget.NONE, DUR_DEFAULT);
        this.isBlind = isBlind;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            AbstractEntity first = AbstractSkill.getTargets(AbstractSkill.SkillTarget.PLAYER_FIRST).get(0);
            for(int i = 1; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if(p.isAlive()) {
                    target.add(p);
                }
            }
            SoundHandler.playSfx("DEBUFF");
            AbstractStatus b = new BlindStatus();
            AbstractStatus u = new UnblockableStatus();
            if(isBlind) {
                first.applyStatus(b, b.amount);
                for (AbstractEntity e : target) {
                    e.applyStatus(u.cpy(), u.amount);
                }
            } else {
                first.applyStatus(u, u.amount);
                for (AbstractEntity e : target) {
                    e.applyStatus(b.cpy(), b.amount);
                }
            }
        }
    }
}
