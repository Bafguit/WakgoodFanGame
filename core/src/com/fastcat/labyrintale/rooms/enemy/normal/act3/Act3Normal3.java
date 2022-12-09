package com.fastcat.labyrintale.rooms.enemy.normal.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal2;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak5;

public class Act3Normal3 extends AbstractRoom {

    public Act3Normal3() {
        super("Act3Normal3", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new Enemy3Normal2(), new Enemy3Weak5(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
