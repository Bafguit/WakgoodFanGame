package com.fastcat.labyrintale.rooms.enemy.normal.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.ModelA;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal1;

public class Act3Normal1 extends AbstractRoom {

  public Act3Normal1() {
    super("Act3Normal1", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new ModelA(), new Enemy3Normal1(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
