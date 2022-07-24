package com.fastcat.labyrintale.rooms.enemy.normal.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.WeakEnemy4;
import com.fastcat.labyrintale.enemies.WeakEnemy5;

public class Normal1 extends AbstractRoom {

    public Normal1() {
        super("Normal1", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new WeakEnemy4(), new WeakEnemy5(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
