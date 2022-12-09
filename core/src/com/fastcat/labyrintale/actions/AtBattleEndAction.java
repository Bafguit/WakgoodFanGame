package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.advisor;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

import com.fastcat.labyrintale.abstracts.*;

public class AtBattleEndAction extends AbstractAction {
    public AtBattleEndAction() {
        super(null, 0);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            for (AbstractPlayer p : players) {
                if (p.isAlive()) {
                    p.pre = null;
                    for (AbstractSkill s : p.hand) {
                        if (s != null) s.atBattleEnd();
                    }
                    p.passive.atBattleEnd();
                    for (AbstractItem m : p.item) {
                        if (m != null) m.atBattleEnd();
                    }
                    for (AbstractStatus s : p.status) {
                        if (s != null) {
                            s.atBattleEnd();
                            s.onRemove();
                        }
                    }
                    p.status.clear();
                }
            }
            advisor.atBattleEnd();
        }
    }
}
