package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.players.TestPlayer;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.handlers.ImageHandler.ENTITY_POINT;

public class EnemyView extends AbstractUI {

    public AbstractEnemy enemy;
    public boolean isLooking = false;
    //public boolean isOnLock = false;

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
        showImg = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);
            //애니메이션
        }
    }

    @Override
    protected void onClick() {
        if(!enemy.isDead) {
            //code
        }
    }
}
