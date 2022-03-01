package com.fastcat.labyrintale.rooms.enemy.elite;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceHolder;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;

public class TestElite extends AbstractRoom {

    public TestElite() {
        super(getEnemies(), 1);
    }

    private static AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new EnemyPlaceHolder(), new EnemyPlaceHolder(), new TestEnemy2(), new TestEnemy() };
    }
}
