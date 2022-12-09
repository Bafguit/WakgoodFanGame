package com.fastcat.labyrintale.rooms.enemy.normal.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.*;
import com.fastcat.labyrintale.enemies.act1.TestEnemy;

public class Normal3 extends AbstractRoom {

    public Normal3() {
        super("Normal3", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new TestEnemy(), new TestEnemy(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
