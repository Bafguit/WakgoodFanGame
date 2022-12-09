package com.fastcat.labyrintale.rooms.enemy.normal.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak6;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal3;

public class Act3Normal5 extends AbstractRoom {

    public Act3Normal5() {
        super("Act3Normal5", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new Enemy3Normal3(), new Enemy2Weak6(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
