package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class TestEnemy extends AbstractEnemy {

  private static final String ID = "TestEnemy1";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 37;

  public TestEnemy() {
    super(ID, TYPE, HEALTH);
    stat.speed = 0;
    stat.debuRes = 10;
    stat.neutRes = 10;
    stat.critical = 10;
    stat.moveRes = 10;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new StrikeE(this));
    temp.add(new GrowE(this));
    return temp;
  }
}
