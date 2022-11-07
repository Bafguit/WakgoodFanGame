package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.MaintainStatus;

public class Enemy2Normal3 extends AbstractEnemy {

  private static final String ID = "Enemy2Normal3";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 20;

  public Enemy2Normal3() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 0;
    stat.critical = 10;
    stat.debuRes = 20;
    stat.neutRes = 5;
    stat.moveRes = 10;
  }

  @Override
  public void preBattle() {
    applyStatus(new MaintainStatus(this), this, 1, false);
    block = 40;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s2 = new UnblockE(this);
    for (int i = 0; i < 3; i++) {
      s2.upgrade();
    }
    temp.add(s2);
    AbstractSkill s1 = new BarrierE(this);
    for (int i = 0; i < 7; i++) {
      s1.upgrade();
    }
    temp.add(s1);
    AbstractSkill s3 = new DoubleE(this);
    for (int i = 0; i < 3; i++) {
      s3.upgrade();
    }
    temp.add(s3);
    AbstractSkill s4 = new StrikeE(this);
    for (int i = 0; i < 4; i++) {
      s4.upgrade();
    }
    temp.add(s4);
    temp.add(new GrowE(this));
    return temp;
  }
}
