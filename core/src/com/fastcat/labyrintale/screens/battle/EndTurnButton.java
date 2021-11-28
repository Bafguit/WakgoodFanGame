package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.actions.EndRoundAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class EndTurnButton extends AbstractUI {

    public EndTurnButton() {
        super(MENU_SELECT, 0, 0, 300, 50);
        setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.35f);
        fontData = MAIN_MENU.cpy();
        text = "턴 종료";
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
            for (int i = 0; i < 4; i++) {
                SkillButton ts = battleScreen.preSkills[i];
                if (ts.skill != null) {
                    ts.skill.use();
                }
            }
            for (int i = 0; i < 4; i++) {
                SkillButton ts = battleScreen.enemySkills[i];
                if (ts.skill != null) {
                    ts.skill.use();
                }
            }
            ActionHandler.bot(new EndRoundAction());
        }
        //Gdx.app.exit();
    }
}