package com.fastcat.labyrintale.rooms.enemy.normal.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.*;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy1;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy3;

public class Normal5 extends AbstractRoom {

    public Normal5() {
        super("Normal5", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new WeakEnemy1(), new WeakEnemy3(), new WeakEnemy1(), new EnemyPlaceholder()};
    }
}
