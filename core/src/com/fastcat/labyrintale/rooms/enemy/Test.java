package com.fastcat.labyrintale.rooms.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;

public class Test extends AbstractRoom {

    public Test() {
        super(getEnemies(), 0);
    }

    private static AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new TestEnemy(), new TestEnemy2(), new TestEnemy2(), new TestEnemy() };
    }
}
