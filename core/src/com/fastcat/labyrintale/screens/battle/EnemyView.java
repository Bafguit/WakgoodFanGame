package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

public class EnemyView extends AbstractUI {

    public AbstractEnemy enemy;
    public boolean isLooking = false;
    public boolean isTarget = false;

    public EnemyView() {
        this(null);
    }

    public EnemyView(AbstractEnemy enemy) {
        super(FileHandler.getUi().get("ENTITY_POINT"));
        this.enemy = enemy;
        showImg = false;
        overable = false;
    }

    @Override
    protected void updateButton() {
        if (battleScreen.isSelecting) {
            overable = enemy.isAlive() && isTarget;
            showImg = over && overable;
            if (showImg) battleScreen.looking.add(enemy);
        } else {
            overable = false;
            showImg = isLooking;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        setPosition(enemy.animX - sWidth / 2, enemy.animY - Gdx.graphics.getHeight() * 0.025f);
        if (enabled && enemy != null && !enemy.isDead) {
            sb.setColor(Color.WHITE);
            if (showImg) sb.draw(img, x, y, sWidth, sHeight);
            enemy.render(sb);
        }
    }

    @Override
    protected void onClick() {
        battleScreen.gets.onTargetSelected(enemy);
    }
}
