package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy4Weak3 extends AbstractEnemy {

  private static final String ID = "Enemy4Weak3";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 34;

  public Enemy4Weak3() {
    super(ID, TYPE, HEALTH);
  }

  @Override
  public void preBattle() {
    applyStatus(new PunishStatus(3), this, 3, false);
    applyStatus(new AttackStatus(2), this, 3, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new AttackLowE(this));
    temp.add(new AttackHighE(this));
    temp.add(new CounterE(this));
    return temp;
  }
}
