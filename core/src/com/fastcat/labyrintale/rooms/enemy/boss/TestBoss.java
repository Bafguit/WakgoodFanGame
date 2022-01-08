package com.fastcat.labyrintale.rooms.enemy.boss;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceHolder;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;

public class TestBoss extends AbstractRoom {

    public TestBoss() {
        super(getEnemies(), 2);
    }

    private static AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new EnemyPlaceHolder(), new EnemyPlaceHolder(), new EnemyPlaceHolder(), new TestEnemy()};
    }
}
