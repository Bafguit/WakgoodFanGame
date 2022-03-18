package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.effects.TurnChangeEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.uis.control.SkillButtonPanel;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

public class PlayerTurnStartAction extends AbstractAction {

    boolean isEnemy;

    public PlayerTurnStartAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            for(AbstractPlayer p : players) {
                if(p.isAlive()) {
                    for (AbstractStatus s : p.status) {
                        if (s != null) s.startOfTurn();
                    }
                }
            }
            if(SkillButtonPanel.noMoreSkill()) ActionHandler.bot(new EndPlayerTurnAction());
        }
    }
}
