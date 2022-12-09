package com.fastcat.labyrintale.rooms.enemy.normal.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal6;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak2;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak4;

public class Act2Normal3 extends AbstractRoom {

    public Act2Normal3() {
        super("Act2Normal3", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy2Weak4(), new Enemy2Normal6(), new Enemy2Weak2(), new EnemyPlaceholder()};
    }
}
