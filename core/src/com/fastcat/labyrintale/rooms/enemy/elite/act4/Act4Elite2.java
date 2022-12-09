package com.fastcat.labyrintale.rooms.enemy.elite.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act4.Enemy4Elite2;
import com.fastcat.labyrintale.enemies.act4.Enemy4Elite3;

public class Act4Elite2 extends AbstractRoom {

    public Act4Elite2() {
        super("Act4Elite2", RoomType.ELITE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new Enemy4Elite2(), new Enemy4Elite3(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
