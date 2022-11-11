package com.fastcat.labyrintale.rooms.enemy.weak.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy5;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak4;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak8;

public class Act2Weak3 extends AbstractRoom {

  public Act2Weak3() {
    super("Act2Weak3", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy2Weak4(), new Enemy2Weak8(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
