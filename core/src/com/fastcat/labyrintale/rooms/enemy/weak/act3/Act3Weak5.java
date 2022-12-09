package com.fastcat.labyrintale.rooms.enemy.weak.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak2;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak7;

public class Act3Weak5 extends AbstractRoom {

    public Act3Weak5() {
        super("Act3Weak5", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy3Weak2(), new Enemy3Weak7(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
