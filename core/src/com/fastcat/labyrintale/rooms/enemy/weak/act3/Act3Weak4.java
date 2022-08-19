package com.fastcat.labyrintale.rooms.enemy.weak.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.BrutalChimp;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak3;

public class Act3Weak4 extends AbstractRoom {

    public Act3Weak4() {
        super("Act3Weak4", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy3Weak3(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
