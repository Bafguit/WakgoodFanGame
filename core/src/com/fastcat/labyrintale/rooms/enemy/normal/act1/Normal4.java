package com.fastcat.labyrintale.rooms.enemy.normal.act1;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.*;
import com.fastcat.labyrintale.enemies.act1.TestEnemy;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy4;

public class Normal4 extends AbstractRoom {

  public Normal4() {
    super("Normal4", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new TestEnemy(), new WeakEnemy4(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
