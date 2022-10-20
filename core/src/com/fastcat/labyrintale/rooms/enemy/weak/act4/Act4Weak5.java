package com.fastcat.labyrintale.rooms.enemy.weak.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.act1.ModelA;
import com.fastcat.labyrintale.enemies.act2.Enemy2Weak6;

public class Act4Weak5 extends AbstractRoom {

  public Act4Weak5() {
    super("Act4Weak5", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new ModelA(), new Enemy2Weak6(), new Enemy2Weak6(), new Enemy2Weak6()
    };
  }
}
