package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.ThrowE;

public class Enemy2Weak4 extends AbstractEnemy {

  private static final String ID = "Enemy2Weak4";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 38;

  public Enemy2Weak4() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 1;
    stat.critical = 5;
    stat.debuRes = 5;
    stat.neutRes = 5;
    stat.moveRes = 30;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new ThrowE(this));
    return temp;
  }
}
