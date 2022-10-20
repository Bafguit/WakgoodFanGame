package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy3Normal3 extends AbstractEnemy {

  private static final String ID = "Enemy3Normal3";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 72;

  public Enemy3Normal3() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
  }

  @Override
  public void preBattle() {
    applyStatus(new AttackStatus(3), this, 3, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s = new UnblockE(this);
    for (int i = 0; i < 2; i++) {
      s.upgrade();
    }
    s.disposable = true;
    temp.add(s);
    temp.add(new ConfuseE(this));
    AbstractSkill s2 = new StrikeE(this);
    for (int i = 0; i < 5; i++) {
      s2.upgrade();
    }
    AbstractSkill s3 = new ChargeE(this);
    s3.upgrade();
    temp.add(s3);
    temp.add(new GrowE(this));
    return temp;
  }
}
