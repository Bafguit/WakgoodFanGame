package com.fastcat.labyrintale.rooms.enemy.normal.act2;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.act2.*;

public class Act2Normal4 extends AbstractRoom {

  public Act2Normal4() {
    super("Act2Normal4", RoomType.BATTLE);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new Enemy2Normal2(), new Enemy2Normal4(), new Enemy2Normal2(), new Enemy2Normal5()
    };
  }
}
