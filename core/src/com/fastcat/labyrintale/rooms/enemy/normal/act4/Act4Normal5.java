package com.fastcat.labyrintale.rooms.enemy.normal.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal1;
import com.fastcat.labyrintale.enemies.act3.Enemy3Normal2;

public class Act4Normal5 extends AbstractRoom {

  public Act4Normal5() {
    super("Act4Normal5", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy3Normal2(), new Enemy3Normal1(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
