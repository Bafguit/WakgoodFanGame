package com.fastcat.labyrintale.rooms.enemy.normal.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal2;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal5;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak6;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak1;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak3;
import com.fastcat.labyrintale.enemies.act4.Enemy4Normal2;

public class Act4Normal3 extends AbstractRoom {

    public Act4Normal3() {
        super("Act4Normal3", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new Enemy3Weak1(), new Enemy3Weak3(), new Enemy2Weak6(), new EnemyPlaceholder()};
    }
}
