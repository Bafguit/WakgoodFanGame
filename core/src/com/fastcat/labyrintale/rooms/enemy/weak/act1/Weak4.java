package com.fastcat.labyrintale.rooms.enemy.weak.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy1;

public class Weak4 extends AbstractRoom {

    public Weak4() {
        super("Weak4", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new WeakEnemy1(), new WeakEnemy1(), new WeakEnemy1(), new EnemyPlaceholder()};
    }
}
