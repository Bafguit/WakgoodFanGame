package com.fastcat.labyrintale.rooms.enemy.boss.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.act2.BossEnemy2;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;

public class Boss2 extends AbstractRoom {

    public Boss2() {
        super("Boss2", RoomType.BOSS);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new BossEnemy2(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
