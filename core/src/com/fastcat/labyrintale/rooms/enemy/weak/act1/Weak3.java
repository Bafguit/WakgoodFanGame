package com.fastcat.labyrintale.rooms.enemy.weak.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.BrutalChimp;

public class Weak3 extends AbstractRoom {

    public Weak3() {
        super("Weak3", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new BrutalChimp(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
