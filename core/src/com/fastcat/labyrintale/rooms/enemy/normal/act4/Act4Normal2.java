package com.fastcat.labyrintale.rooms.enemy.normal.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal2;
import com.fastcat.labyrintale.enemies.act4.Enemy4Normal1;
import com.fastcat.labyrintale.enemies.act4.Enemy4Normal2;

public class Act4Normal2 extends AbstractRoom {

    public Act4Normal2() {
        super("Act4Normal2", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy2Normal2(), new Enemy4Normal2(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
