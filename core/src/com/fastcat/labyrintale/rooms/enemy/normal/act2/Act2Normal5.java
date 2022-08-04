package com.fastcat.labyrintale.rooms.enemy.normal.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal2;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal4;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal5;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak3;

public class Act2Normal5 extends AbstractRoom {

    public Act2Normal5() {
        super("Act2Normal5", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy2Weak3(), new Enemy2Weak3(), new Enemy2Weak3(), new EnemyPlaceholder()};
    }
}
