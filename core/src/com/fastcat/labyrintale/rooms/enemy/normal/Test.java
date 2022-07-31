package com.fastcat.labyrintale.rooms.enemy.normal;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.act1.ModelA;
import com.fastcat.labyrintale.enemies.act1.TestEnemy;

public class Test extends AbstractRoom {

    public Test() {
        super("Test", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new TestEnemy(), new ModelA(), new ModelA(), new TestEnemy()};
    }
}
