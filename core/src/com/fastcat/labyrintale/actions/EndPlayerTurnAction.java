package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.battle.SkillButton;
import com.fastcat.labyrintale.uis.control.SkillButtonPanel;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

public class EndPlayerTurnAction extends AbstractAction {
    public EndPlayerTurnAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            for(AbstractPlayer p : players) {
                if(p.isAlive()) {
                    for (AbstractStatus s : p.status) {
                        if (s != null) s.endOfTurn();
                    }
                }
            }
            ActionHandler.bot(new TurnChangeAction(true));
            ActionHandler.bot(new EnemyTurnStartAction());
            ActionHandler.bot(new RemoveAllBlockAction(true));
            for (int i = 0; i < 4; i++) {
                SkillButton ts = battleScreen.enemySkills[i];
                if (ts.skill != null) {
                    ts.skill.useCard();
                }
            }
            for(AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                if(e.isAlive()) {
                    for (int i = 0; i < 5; i++) {
                        if (e.status[i] != null) e.status[i].endOfTurn();
                    }
                }
            }
            ActionHandler.bot(new RemoveAllBlockAction(false));
            ActionHandler.bot(new EndRoundAction());
            ActionHandler.bot(new TurnChangeAction(false));
            ActionHandler.bot(new PlayerTurnStartAction());
        }
    }
}
