package com.fastcat.labyrintale.rooms.enemy.boss;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.TestEnemy;

public class TestBoss extends AbstractRoom {

    public TestBoss() {
        super(getEnemies(), 2);
    }

    private static AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder(), new TestEnemy()};
    }
}
