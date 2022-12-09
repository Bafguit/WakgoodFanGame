package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

import com.fastcat.labyrintale.abstracts.*;

public class StartBattleAction extends AbstractAction {

    public StartBattleAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            AbstractLabyrinth.advisor.atBattleStart();
            for (AbstractEntity p : battleScreen.getTurns()) {
                if (p.isPlayer) {
                    for (AbstractSkill s : p.hand) {
                        if (s != null) s.atBattleStart();
                    }
                    p.passive.atBattleStart();
                    for (AbstractItem m : p.item) {
                        if (m != null) m.atBattleStart();
                    }
                }
            }
        }
    }
}
