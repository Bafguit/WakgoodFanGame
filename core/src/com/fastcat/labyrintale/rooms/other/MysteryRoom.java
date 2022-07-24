package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;

public class MysteryRoom extends AbstractRoom {

    private static final String ID = "Mystery";

    public MysteryRoom() {
        super(ID, RoomType.MYSTERY);
    }

    @Override
    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
}
