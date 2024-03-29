package com.fastcat.labyrintale.rooms.enemy.elite.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy4;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy5;
import com.fastcat.labyrintale.enemies.act3.Enemy3Elite1;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal5;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak1;

public class Act3Elite1 extends AbstractRoom {

    public Act3Elite1() {
        super("Act3Elite1", RoomType.ELITE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {new Enemy3Elite1(), new Enemy3Weak1(), new Enemy3Normal5(), new EnemyPlaceholder()};
    }
}
