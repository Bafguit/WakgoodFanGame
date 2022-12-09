package com.fastcat.labyrintale.rooms.enemy.normal.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak6;

public class Act3Normal4 extends AbstractRoom {

    public Act3Normal4() {
        super("Act3Normal4", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy3Weak6(), new Enemy3Weak6(), new Enemy3Weak6(), new EnemyPlaceholder()};
    }
}
