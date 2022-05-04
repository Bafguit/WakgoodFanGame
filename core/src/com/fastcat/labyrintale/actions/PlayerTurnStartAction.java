package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.effects.TurnChangeEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
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
