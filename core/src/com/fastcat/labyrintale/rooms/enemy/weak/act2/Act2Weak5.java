package com.fastcat.labyrintale.rooms.enemy.weak.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak10;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak4;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak9;

public class Act2Weak5 extends AbstractRoom {

    public Act2Weak5() {
        super("Act2Weak5", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy2Weak4(), new Enemy2Weak9(), new Enemy2Weak10(), new EnemyPlaceholder()};
    }
}
