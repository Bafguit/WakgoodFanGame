package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.SelfBombStatus;

public class Enemy2Normal4 extends AbstractEnemy {

  private static final String ID = "Enemy2Normal4";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 30;

  public Enemy2Normal4() {
    super(ID, TYPE, HEALTH);
    stat.speed = 0;
    stat.critical = 5;
    stat.debuRes = 30;
    stat.neutRes = 5;
    stat.moveRes = 10;
  }

  @Override
  public void preBattle() {
    applyStatus(new SelfBombStatus(5), this, 5, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s1 = new BarrierE(this);
    for (int i = 0; i < 3; i++) {
      s1.upgrade();
    }
    temp.add(s1);
    AbstractSkill s2 = new StrikeE(this);
    for (int i = 0; i < 1; i++) {
      s2.upgrade();
    }
    temp.add(s2);
    AbstractSkill s3 = new FrailStrongE(this);
    for (int i = 0; i < 1; i++) {
      s3.upgrade();
    }
    temp.add(s3);
    AbstractSkill s4 = new CorrosE(this);
    for (int i = 0; i < 1; i++) {
      s4.upgrade();
    }
    temp.add(s4);
    return temp;
  }
}
