package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.DoubleE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.status.ArmourStatus;

public class Enemy2Normal1 extends AbstractEnemy {

  private static final String ID = "Enemy2Normal1";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 51;

  public Enemy2Normal1() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 1;
    stat.critical = 20;
    stat.debuRes = 20;
    stat.neutRes = 5;
    stat.moveRes = 10;
  }

  @Override
  public void preBattle() {
    applyStatus(new ArmourStatus(10), this, 10, false);
    block = 10;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s1 = new DoubleE(this);
    for (int i = 0; i < 3; i++) {
      s1.upgrade();
    }
    temp.add(s1);
    AbstractSkill s2 = new StrikeE(this);
    for (int i = 0; i < 4; i++) {
      s2.upgrade();
    }
    temp.add(s2);
    AbstractSkill s3 = new FuryE(this);
    s3.upgrade();
    s3.upgrade();
    temp.add(s3);
    return temp;
  }
}
