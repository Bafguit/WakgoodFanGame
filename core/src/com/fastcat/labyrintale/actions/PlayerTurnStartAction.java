package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.uis.control.SkillButtonPanel;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

public class PlayerTurnStartAction extends AbstractAction {

    boolean isFirst;

    public PlayerTurnStartAction() {
        super(null, 0.5f);
        isFirst = false;
    }

    public PlayerTurnStartAction(boolean first) {
        super(null, 0.5f);
        isFirst = first;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            if(isFirst) AbstractLabyrinth.advisor.skill.atBattleStart();
            for(AbstractPlayer p : players) {
                if(p.isAlive()) {
                    if(isFirst) {
                        for (AbstractItem m : p.item) {
                            if (m != null) m.atBattleStart();
                        }
                    }
                    for (AbstractItem m : p.item) {
                        if (m != null) m.startOfTurn();
                    }
                    for (AbstractStatus s : p.status) {
                        if (s != null) s.startOfTurn();
                    }
                }
            }
            if(SkillButtonPanel.noMoreSkill()) ActionHandler.bot(new EndPlayerTurnAction());
        }
    }
}
