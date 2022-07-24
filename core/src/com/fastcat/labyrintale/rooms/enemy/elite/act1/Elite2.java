package com.fastcat.labyrintale.rooms.enemy.elite.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.TestEnemy2;
import com.fastcat.labyrintale.enemies.WeakEnemy2;

public class Elite2 extends AbstractRoom {

    public Elite2() {
        super("Elite2", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new TestEnemy2(), new WeakEnemy2(), new WeakEnemy2(), new WeakEnemy2()};
    }
}
