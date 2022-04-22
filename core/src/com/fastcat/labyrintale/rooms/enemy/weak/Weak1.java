package com.fastcat.labyrintale.rooms.enemy.weak;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceHolder;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;
import com.fastcat.labyrintale.enemies.WeakEnemy1;

public class Weak1 extends AbstractRoom {

    public Weak1() {
        super(getEnemies(), 0);
    }

    private static AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] { new EnemyPlaceHolder(), new WeakEnemy1(), new WeakEnemy1(), new WeakEnemy1() };
    }
}