package com.fastcat.labyrintale.rooms.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;

public class Test extends AbstractRoom {

    private static final AbstractEnemy[] ENEMIES = new AbstractEnemy[] {new TestEnemy(), new TestEnemy2(), new TestEnemy2(), new TestEnemy()};

    public Test() {
        super(ENEMIES, 0);
    }
}
