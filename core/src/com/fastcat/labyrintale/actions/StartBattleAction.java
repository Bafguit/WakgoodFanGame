package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;

import java.util.Comparator;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

public class StartBattleAction extends AbstractAction {

    boolean isFirst;

    public StartBattleAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            AbstractLabyrinth.advisor.skill.atBattleStart();
            for (AbstractEntity p : battleScreen.getTurns()) {
                if (p.isPlayer) {
                    for (AbstractSkill s : p.hand) {
                        if(s != null) s.atBattleStart();
                    }
                    for (AbstractItem m : p.item) {
                        if (m != null) m.atBattleStart();
                    }
                }
            }
        }
    }
}
