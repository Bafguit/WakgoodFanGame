package com.fastcat.labyrintale.rooms.enemy.weak.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy6;

public class Weak5 extends AbstractRoom {

    public Weak5() {
        super("Weak5", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new WeakEnemy6(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
