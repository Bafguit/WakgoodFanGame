package com.fastcat.labyrintale.rooms.enemy.normal.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy1;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy6;

public class Normal2 extends AbstractRoom {

    public Normal2() {
        super("Normal2", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new WeakEnemy1(), new WeakEnemy1(), new WeakEnemy6(), new EnemyPlaceholder()};
    }
}
