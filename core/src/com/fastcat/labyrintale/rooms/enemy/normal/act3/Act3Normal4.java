package com.fastcat.labyrintale.rooms.enemy.normal.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak4;

public class Act3Normal4 extends AbstractRoom {

  public Act3Normal4() {
    super("Act3Normal4", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy2Weak4(), new Enemy2Weak4(), new Enemy2Weak4(), new EnemyPlaceholder()
    };
  }
}
