package com.fastcat.labyrintale.rooms.enemy.weak.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal4;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal5;

public class Act3Weak2 extends AbstractRoom {

    public Act3Weak2() {
        super("Act3Weak2", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new Enemy3Normal4(), new Enemy3Normal4(), new Enemy3Normal5(), new EnemyPlaceholder()
        };
    }
}
