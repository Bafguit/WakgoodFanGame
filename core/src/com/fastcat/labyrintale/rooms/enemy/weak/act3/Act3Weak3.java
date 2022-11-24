package com.fastcat.labyrintale.rooms.enemy.weak.act3;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act1.BrutalChimp;
import com.fastcat.labyrintale.enemies.act3.Enemy3Weak8;

public class Act3Weak3 extends AbstractRoom {

  public Act3Weak3() {
    super("Act3Weak3", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy3Weak8(), new Enemy3Weak8(), new Enemy3Weak8(), new EnemyPlaceholder()
    };
  }
}
