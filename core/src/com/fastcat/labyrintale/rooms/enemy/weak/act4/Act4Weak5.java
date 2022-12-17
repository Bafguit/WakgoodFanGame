package com.fastcat.labyrintale.rooms.enemy.weak.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak6;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak7;
import com.fastcat.labyrintale.enemies.act4.Enemy4Weak7;
import com.fastcat.labyrintale.enemies.act4.Enemy4Weak8;

public class Act4Weak5 extends AbstractRoom {

    public Act4Weak5() {
        super("Act4Weak5", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy4Weak7(), new Enemy4Weak8(), new Enemy4Weak8(), new Enemy4Weak8()};
    }
}
