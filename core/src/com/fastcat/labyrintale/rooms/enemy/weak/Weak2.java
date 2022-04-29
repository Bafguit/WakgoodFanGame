package com.fastcat.labyrintale.rooms.enemy.weak;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceHolder;
import com.fastcat.labyrintale.enemies.TestEnemy2;
import com.fastcat.labyrintale.enemies.WeakEnemy1;
import com.fastcat.labyrintale.enemies.WeakEnemy2;

public class Weak2 extends AbstractRoom {

    public Weak2() {
        super(getEnemies(), 0);
    }

    private static AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] { new EnemyPlaceHolder(), new TestEnemy2(), new EnemyPlaceHolder(), new WeakEnemy2() };
    }
}
