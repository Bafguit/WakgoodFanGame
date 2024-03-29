package com.fastcat.labyrintale.rooms.enemy.elite.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Elite1;
import com.fastcat.labyrintale.enemies.act2.Enemy2Elite3;

public class Act2Elite1 extends AbstractRoom {

    public Act2Elite1() {
        super("Act2Elite1", RoomType.ELITE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy2Elite1(), new Enemy2Elite3(), new Enemy2Elite3(), new EnemyPlaceholder()};
    }
}
