package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy2Elite1 extends AbstractEnemy {

  private static final String ID = "Enemy2Elite1";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 63;

  public Enemy2Elite1() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
  }

  @Override
  public void preBattle() {
    applyStatus(new PunishStatus(1), this, 1, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new InduceE(this));
    AbstractSkill s = new FourE(this);
    s.upgrade();
    temp.add(s);
    AbstractSkill s1 = new DualAttackE(this);
    for (int i = 0; i < 4; i++) {
      s1.upgrade();
    }
    temp.add(s1);
    temp.add(new GrowE(this));
    return temp;
  }
}
