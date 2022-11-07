package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class Enemy4Normal2 extends AbstractEnemy {

  private static final String ID = "Enemy4Normal2";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 67;

  public Enemy4Normal2() {
    super(ID, TYPE, HEALTH);
    stat.speed = 1;
    stat.critical = 20;
    stat.debuRes = 20;
    stat.neutRes = 10;
    stat.moveRes = 10;
  }

  @Override
  public void preBattle() {}

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new AccelE(this));
    return temp;
  }
}
