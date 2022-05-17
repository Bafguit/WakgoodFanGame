package com.fastcat.labyrintale.rooms.enemy.weak;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.TestEnemy2;
import com.fastcat.labyrintale.enemies.WeakEnemy2;

public class Weak2 extends AbstractRoom {

    public Weak2() {
        super("Weak2", RoomType.BATTLE);
    }

    @Override
    protected AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] { new EnemyPlaceholder(), new TestEnemy2(), new EnemyPlaceholder(), new WeakEnemy2() };
    }
}
