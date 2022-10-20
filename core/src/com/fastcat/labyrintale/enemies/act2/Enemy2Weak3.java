package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class Enemy2Weak3 extends AbstractEnemy {

  private static final String ID = "Enemy2Weak3";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 39;

  public Enemy2Weak3() {
    super(ID, TYPE, HEALTH);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new FourE(this));
    AbstractSkill s = new StrikeE(this);
    for (int i = 0; i < 5; i++) {
      s.upgrade();
    }
    temp.add(s);
    temp.add(new FuryE(this));
    return temp;
  }
}
