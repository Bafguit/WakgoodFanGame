package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.events.FloorFourthEvent;
import com.fastcat.labyrintale.events.FloorSecondEvent;

public class FourthFloorRoom extends AbstractRoom {
  public FourthFloorRoom() {
    super(new FloorFourthEvent());
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
