package com.fastcat.labyrintale.rooms.enemy.normal.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal6;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak2;

public class Act3Normal2 extends AbstractRoom {

    public Act3Normal2() {
        super("Act3Normal2", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new Enemy3Normal6(), new Enemy3Weak2(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
