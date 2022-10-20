package com.fastcat.labyrintale.rooms.enemy.elite.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.act1.ModelA;
import com.fastcat.labyrintale.enemies.act1.ModelB;

public class Elite2 extends AbstractRoom {

  public Elite2() {
    super("Elite2", RoomType.ELITE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {new ModelA(), new ModelB(), new ModelB(), new ModelB()};
  }
}
