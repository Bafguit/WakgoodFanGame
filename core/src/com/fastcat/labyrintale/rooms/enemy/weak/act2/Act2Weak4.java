package com.fastcat.labyrintale.rooms.enemy.weak.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.WeakEnemy6;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak1;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak7;

public class Act2Weak4 extends AbstractRoom {

  public Act2Weak4() {
    super("Act2Weak4", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy2Weak1(), new Enemy2Weak7(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
