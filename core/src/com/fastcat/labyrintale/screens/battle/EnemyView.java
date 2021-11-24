package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.ENTITY_POINT;

public class EnemyView extends AbstractUI {

    public AbstractEnemy enemy;
    public boolean isLooking = false;

    public EnemyView() {
        this(null);
    }

    public EnemyView(AbstractEnemy enemy) {
        super(ENTITY_POINT);
        this.enemy = enemy;
        showImg = false;
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
        if(enabled) {
            sb.setColor(Color.WHITE);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);
            if(enemy != null) enemy.render(sb);
            //애니메이션
        }
    }

    @Override
    protected void onClick() {
        /*if(!enemy.isDead) {
            //code
        }*/
    }
}
