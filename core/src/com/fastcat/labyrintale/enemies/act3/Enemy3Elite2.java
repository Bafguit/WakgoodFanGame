package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class Enemy3Elite2 extends AbstractEnemy {

  private static final String ID = "Enemy3Elite2";
  private static final EnemyType TYPE = EnemyType.ELITE;
  private static final int HEALTH = 100;

  public Enemy3Elite2() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 3;
    stat.critical = 20;
    stat.debuRes = 20;
    stat.neutRes = 10;
    stat.moveRes = 10;
  }

  @Override
  public void preBattle() {
    applyStatus(new ImmuneStatus(3), this, 3, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s2 = new CorrosE(this);
    for (int i = 0; i < 4; i++) {
      s2.upgrade();
    }
    AbstractSkill s = new HinderAllE(this);
    for (int i = 0; i < 3; i++) {
      s.upgrade();
    }
    temp.add(s);
    AbstractSkill s1 = new AllTwoE(this).upgrade();
    s1.upgrade();
    s1.upgrade();
    temp.add(s1);
    temp.add(new GrowE(this));
    return temp;
  }
}
