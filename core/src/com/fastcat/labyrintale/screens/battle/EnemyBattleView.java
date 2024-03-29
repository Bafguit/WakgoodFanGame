package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class EnemyBattleView extends BattleView {

    public EnemyBattleView(AbstractEnemy enemy) {
        super(
                enemy != null && enemy.type == AbstractEnemy.EnemyType.BOSS
                        ? FileHandler.getUi().get("ENTITY_POINT_B")
                        : FileHandler.getUi().get("ENTITY_POINT"));
        this.entity = enemy;
        showImg = false;
        overable = false;

        if (enemy != null && enemy.type == AbstractEnemy.EnemyType.BOSS) {
            turnLook = new TempUI(FileHandler.getUi().get("POINT_TURN_B"));
            targetLook = new TempUI(FileHandler.getUi().get("POINT_ALLEY_B"));
            statSize = 9;
        }
    }

    @Override
    protected void updateButton() {
        if (battleScreen.isSelecting) {
            overable = entity.isAlive() && isTarget;
            clickable = overable;
            showImg = overable && over;
            if (showImg) battleScreen.looking.add(entity);
        } else {
            overable = entity.isAlive();
            clickable = false;
            AbstractEntity t = battleScreen.currentTurnEntity();
            showImg = isLooking || (t != null && t == entity);
            if (overable && over) {
                AbstractLabyrinth.cPanel.battlePanel.setEntity(entity);
            }
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        setPosition(entity.animX - sWidth / 2, entity.animY - 36 * InputHandler.scale);
        if (enabled && entity != null && !entity.isDead) {
            sb.setColor(Color.WHITE);
            entity.render(sb);
        }
    }

    @Override
    protected void onClick() {
        battleScreen.gets.onTargetSelected(entity);
    }
}
