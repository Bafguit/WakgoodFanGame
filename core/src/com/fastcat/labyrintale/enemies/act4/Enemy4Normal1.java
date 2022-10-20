package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy4Normal1 extends AbstractEnemy {

  private static final String ID = "Enemy4Normal1";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 44;

  public Enemy4Normal1() {
    super(ID, TYPE, HEALTH);
  }

  @Override
  public void preBattle() {
    applyStatus(new AttackStatus(4), this, 4, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new AttackHighE(this));
    temp.add(new AttackLowE(this));
    temp.add(new FrailStrongE(this));
    AbstractSkill s = new FuryE(this);
    s.upgrade();
    temp.add(s);
    return temp;
  }
}
