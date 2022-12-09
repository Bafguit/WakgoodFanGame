package com.fastcat.labyrintale.rooms.enemy.normal.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act4.Enemy4Normal3;

public class Act4Normal4 extends AbstractRoom {

    public Act4Normal4() {
        super("Act4Normal4", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new Enemy4Normal3(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
