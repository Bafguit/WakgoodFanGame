package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.BugEnemy;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;

public class BugRoom extends AbstractRoom {
    public BugRoom() {
        super("Bug", RoomType.BATTLE);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[] {
            new BugEnemy(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()
        };
    }
}
