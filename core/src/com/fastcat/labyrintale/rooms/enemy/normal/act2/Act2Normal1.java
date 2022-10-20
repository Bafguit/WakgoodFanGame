package com.fastcat.labyrintale.rooms.enemy.normal.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Normal1;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak5;

public class Act2Normal1 extends AbstractRoom {

  public Act2Normal1() {
    super("Act2Normal1", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy2Weak5(), new Enemy2Normal1(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
