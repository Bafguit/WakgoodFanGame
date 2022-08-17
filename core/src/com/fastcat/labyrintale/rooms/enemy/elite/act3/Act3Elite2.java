package com.fastcat.labyrintale.rooms.enemy.elite.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy4;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy5;
import com.fastcat.labyrintale.enemies.act3.Enemy3Elite1;
import com.fastcat.labyrintale.enemies.act3.Enemy3Elite2;

public class Act3Elite2 extends AbstractRoom {

    public Act3Elite2() {
        super("Act3Elite1", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy3Elite2(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
