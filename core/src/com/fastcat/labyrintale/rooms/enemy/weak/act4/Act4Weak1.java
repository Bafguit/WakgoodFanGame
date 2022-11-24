package com.fastcat.labyrintale.rooms.enemy.weak.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak5;
import com.fastcat.labyrintale.enemies.act4.Enemy4Weak1;

public class Act4Weak1 extends AbstractRoom {

  public Act4Weak1() {
    super("Act4Weak1", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy2Weak5(), new Enemy2Weak5(), new Enemy4Weak1(), new EnemyPlaceholder()
    };
  }
}
