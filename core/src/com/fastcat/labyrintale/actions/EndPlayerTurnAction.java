package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.battle.SkillButton;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

public class EndPlayerTurnAction extends AbstractAction {
    public EndPlayerTurnAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            ActionHandler.bot(new EndTurnAction(true));
            ActionHandler.bot(new TurnChangeAction(true));
            ActionHandler.bot(new RemoveAllBlockAction(true));
            ActionHandler.bot(new EnemyTurnStartAction());
            for (int i = 0; i < 4; i++) {
                SkillButton ts = battleScreen.enemySkills[i];
                if (ts.skill != null) {
                    ts.skill.useCard();
                }
            }
            ActionHandler.bot(new EndTurnAction(false));
            ActionHandler.bot(new BeforeEndRoundAction());
            ActionHandler.bot(new EndRoundAction());
            ActionHandler.bot(new TurnChangeAction(false));
            ActionHandler.bot(new RemoveAllBlockAction(false));
            ActionHandler.bot(new PlayerTurnStartAction());
        }
    }
}
