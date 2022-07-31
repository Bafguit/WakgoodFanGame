package com.fastcat.labyrintale.rooms.enemy.elite;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.ModelA;
import com.fastcat.labyrintale.enemies.act1.TestEnemy;

public class TestElite extends AbstractRoom {

    public TestElite() {
        super("TestElite", RoomType.ELITE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new ModelA(), new TestEnemy(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
