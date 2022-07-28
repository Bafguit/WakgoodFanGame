package com.fastcat.labyrintale.rooms.enemy.elite.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.*;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy7;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy8;

public class Elite1 extends AbstractRoom {

    public Elite1() {
        super("Elite1", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new WeakEnemy7(), new WeakEnemy8(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
