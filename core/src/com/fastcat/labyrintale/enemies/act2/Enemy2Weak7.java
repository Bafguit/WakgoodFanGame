package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.UnstoppableE;

public class Enemy2Weak7 extends AbstractEnemy {

  private static final String ID = "Enemy2Weak7";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 42;

  public Enemy2Weak7() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 0;
    stat.debuRes = 5;
    stat.neutRes = 20;
    stat.critical = 5;
    stat.moveRes = 5;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s = new GrowE(this);
    s.disposable = true;
    temp.add(s);
    temp.add(new UnstoppableE(this));
    return temp;
  }
}
