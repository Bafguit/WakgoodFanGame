package com.fastcat.labyrintale.rooms.enemy.common;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;

public class Test extends AbstractRoom {

    public Test() {
        super(getEnemies(), 0);
    }

    private static AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new TestEnemy(0), new TestEnemy2(1), new TestEnemy2(2), new TestEnemy(3) };
    }
}
