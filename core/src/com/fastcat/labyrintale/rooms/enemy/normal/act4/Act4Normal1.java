package com.fastcat.labyrintale.rooms.enemy.normal.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act4.Enemy4Normal1;

public class Act4Normal1 extends AbstractRoom {

    public Act4Normal1() {
        super("Act4Normal1", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new Enemy4Normal1(), new Enemy4Normal1(), new Enemy4Normal1(), new EnemyPlaceholder()
        };
    }
}
