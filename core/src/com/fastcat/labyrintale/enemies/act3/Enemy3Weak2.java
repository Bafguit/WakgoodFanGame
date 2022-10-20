package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class Enemy3Weak2 extends AbstractEnemy {

  private static final String ID = "Enemy3Weak2";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 52;

  public Enemy3Weak2() {
    super(ID, TYPE, HEALTH);
  }

  @Override
  public void preBattle() {
    applyStatus(new ImmuneStatus(3), this, 3, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s1 = new AttackLowE(this);
    for (int i = 0; i < 3; i++) {
      s1.upgrade();
    }
    temp.add(s1);
    AbstractSkill s4 = new AttackLowE(this);
    for (int i = 0; i < 3; i++) {
      s4.upgrade();
    }
    temp.add(s4);
    AbstractSkill s2 = new FuryE(this);
    s2.upgrade();
    s2.upgrade();
    temp.add(s2);
    AbstractSkill s3 = new WeakE(this);
    s3.upgrade();
    temp.add(s3);
    return temp;
  }
}
