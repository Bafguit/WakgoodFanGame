package com.fastcat.labyrintale.rooms.enemy.elite.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.*;

public class Act2Elite2 extends AbstractRoom {

    public Act2Elite2() {
        super("Act2Elite2", RoomType.ELITE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy2Weak2(), new Enemy2Normal1(), new Enemy2Normal5(), new EnemyPlaceholder()};
    }
}
