package com.fastcat.labyrintale.rooms.enemy.normal.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal3;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak3;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal2;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak2;

public class Act3Normal3 extends AbstractRoom {

    public Act3Normal3() {
        super("Act3Normal2", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy2Weak3(), new Enemy3Normal2(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
