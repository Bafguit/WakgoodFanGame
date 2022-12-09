package com.fastcat.labyrintale.rooms.enemy.weak.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act4.Enemy4Weak3;

public class Act4Weak4 extends AbstractRoom {

    public Act4Weak4() {
        super("Act4Weak4", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy4Weak3(), new Enemy4Weak3(), new Enemy4Weak3(), new EnemyPlaceholder()};
    }
}
