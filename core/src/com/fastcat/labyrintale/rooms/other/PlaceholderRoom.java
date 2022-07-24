package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;

public class PlaceholderRoom extends AbstractRoom {
    public PlaceholderRoom() {
        super("Placeholder", RoomType.ENTRY);
        isDone = true;
        battleDone = true;
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
