package com.fastcat.labyrintale.rooms.enemy.boss.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.BossEnemy1;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;

public class Boss3 extends AbstractRoom {

    public Boss3() {
        super("Boss3", RoomType.BOSS);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new BossEnemy1(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
