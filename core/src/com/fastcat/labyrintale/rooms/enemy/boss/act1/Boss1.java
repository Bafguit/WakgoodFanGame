package com.fastcat.labyrintale.rooms.enemy.boss.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.act1.BossEnemy1;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;

public class Boss1 extends AbstractRoom {

    public Boss1() {
        super("Boss1", RoomType.BOSS);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new BossEnemy1(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
