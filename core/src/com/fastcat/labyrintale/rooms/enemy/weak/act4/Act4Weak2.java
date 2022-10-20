package com.fastcat.labyrintale.rooms.enemy.weak.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act4.Enemy4Weak2;

public class Act4Weak2 extends AbstractRoom {

  public Act4Weak2() {
    super("Act4Weak2", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy4Weak2(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
