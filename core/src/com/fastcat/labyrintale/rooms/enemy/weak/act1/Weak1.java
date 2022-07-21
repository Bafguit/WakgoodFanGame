package com.fastcat.labyrintale.rooms.enemy.weak.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.WeakEnemy1;

public class Weak1 extends AbstractRoom {

    public Weak1() {
        super("Weak1", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] { new WeakEnemy1(), new WeakEnemy1(), new WeakEnemy1(), new EnemyPlaceholder()};
    }
}
