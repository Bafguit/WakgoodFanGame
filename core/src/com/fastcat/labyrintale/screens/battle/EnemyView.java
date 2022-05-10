package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class EnemyView extends AbstractUI {

    public AbstractEnemy enemy;
    public boolean isLooking = false;

    public EnemyView() {
        this(null);
    }

    public EnemyView(AbstractEnemy enemy) {
        super(FileHandler.ui.get("ENTITY_POINT"));
        this.enemy = enemy;
        showImg = false;
        overable = false;
    }

    @Override
    protected void updateButton() {
        if(isLooking) showImg = true;
        else showImg = false;
    }

    @Override
    protected void onOver() {
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled && enemy != null && !enemy.isDead) {
            sb.setColor(Color.WHITE);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);
            enemy.render(sb);
        }
    }
}
