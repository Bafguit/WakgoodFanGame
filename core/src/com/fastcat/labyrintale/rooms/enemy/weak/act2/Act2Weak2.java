package com.fastcat.labyrintale.rooms.enemy.weak.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy6;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak2;

public class Act2Weak2 extends AbstractRoom {

  public Act2Weak2() {
    super("Act2Weak2", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new WeakEnemy6(), new Enemy2Weak2(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
