package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.OpenStatus;

public class Enemy4Elite1 extends AbstractEnemy {

  private static final String ID = "Enemy4Elite1";
  private static final EnemyType TYPE = EnemyType.ELITE;
  private static final int HEALTH = 220;

  public Enemy4Elite1() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
  }

  @Override
  public void preBattle() {
    applyStatus(new OpenStatus(1), this, 1, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s = new StrikeE(this);
    for (int i = 0; i < 4; i++) {
      s.upgrade();
    }
    s.disposable = true;
    temp.add(s);
    AbstractSkill s2 = new FrailStrongE(this);
    for (int i = 0; i < 1; i++) {
      s2.upgrade();
    }
    s2.disposable = true;
    temp.add(s2);
    AbstractSkill s1 = new HinderAllE(this);
    for (int i = 0; i < 1; i++) {
      s1.upgrade();
    }
    s1.disposable = true;
    temp.add(s1);
    AbstractSkill s3 = new DecayE(this);
    s3.upgrade();
    s3.disposable = true;
    temp.add(s3);
    AbstractSkill s4 = new UnstoppableE(this);
    for (int i = 0; i < 3; i++) {
      s4.upgrade();
    }
    temp.add(s4);
    return temp;
  }
}
