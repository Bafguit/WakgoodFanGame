package com.fastcat.labyrintale.rooms.enemy.boss.act4;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.enemies.act4.BossEnemy4;

public class Boss4 extends AbstractRoom {

  public Boss4() {
    super("Boss4", RoomType.BOSS);
  }

  @Override
  public AbstractEnemy[] getEnemies() {
    return new AbstractEnemy[] {
      new BossEnemy4(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()
    };
  }
}
