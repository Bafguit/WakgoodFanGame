package com.fastcat.labyrintale.rooms.enemy.weak.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak1;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak2;
import com.fastcat.labyrintale.enemies.act4.Enemy4Weak4;

public class Act4Weak3 extends AbstractRoom {

  public Act4Weak3() {
    super("Act4Weak3", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy2Weak1(), new Enemy4Weak4(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
