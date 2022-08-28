package com.fastcat.labyrintale.rooms.enemy.elite.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy4;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy5;
import com.fastcat.labyrintale.enemies.act3.Enemy3Elite1;
import com.fastcat.labyrintale.enemies.act4.Enemy4Elite1;

public class Act4Elite1 extends AbstractRoom {

    public Act4Elite1() {
        super("Act4Elite1", RoomType.ELITE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy4Elite1(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
