package com.fastcat.labyrintale.rooms.enemy.weak.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak1;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak5;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak1;

public class Act3Weak1 extends AbstractRoom {

    public Act3Weak1() {
        super("Act2Weak1", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy2Weak5(), new Enemy3Weak1(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
