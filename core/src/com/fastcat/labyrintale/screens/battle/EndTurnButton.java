package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.EndRoundAction;
import com.fastcat.labyrintale.actions.RemoveAllBlockAction;
import com.fastcat.labyrintale.actions.TurnChangeAction;
import com.fastcat.labyrintale.actions.WaitAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class EndTurnButton extends AbstractUI {

    public EndTurnButton() {
        super(MENU_SELECT, 0, 0, 300, 50);
        setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.35f);
        fontData = MAIN_MENU;
        text = "턴 시작";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if(!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        if(!battleScreen.isEnemyTurn && !ActionHandler.isRunning) {
            ActionHandler.bot(new TurnChangeAction(true));
            ActionHandler.bot(new WaitAction(0.5f));
            for(AbstractPlayer p : players) {
                for(AbstractStatus s : p.status) {
                    if(s != null) s.startOfTurn();
                }
            }
            for (int i = 0; i < 4; i++) {
                SkillButton ts = battleScreen.preSkills[i];
                if (ts.skill != null) {
                    ts.skill.useCard();
                }
            }
            for(AbstractPlayer p : players) {
                for(AbstractStatus s : p.status) {
                    if(s != null) s.endOfTurn();
                }
            }
            for(AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                for(AbstractStatus s : e.status) {
                    if(s != null) s.startOfTurn();
                }
            }
            ActionHandler.bot(new RemoveAllBlockAction(true));
            for (int i = 0; i < 4; i++) {
                SkillButton ts = battleScreen.enemySkills[i];
                if (ts.skill != null) {
                    ts.skill.useCard();
                }
            }
            for(AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                for(AbstractStatus s : e.status) {
                    if(s != null) s.endOfTurn();
                }
            }
            ActionHandler.bot(new RemoveAllBlockAction(false));
            ActionHandler.bot(new EndRoundAction());
            ActionHandler.bot(new TurnChangeAction(false));
        }
        //Gdx.app.exit();
    }
}
