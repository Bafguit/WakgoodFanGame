package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.UnblockE;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy4Normal4 extends AbstractEnemy {

  private static final String ID = "Enemy3Weak1";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 56;

  public Enemy4Normal4() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 3;
    stat.critical = 5;
    stat.debuRes = 30;
    stat.neutRes = 15;
    stat.moveRes = 10;
  }

  @Override
  public void preBattle() {
    applyStatus(new AttackStatus(3), this, 4, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new UnblockE(this).upgrade());
    temp.add(new UnblockE(this).upgrade());
    temp.add(new UnblockE(this).upgrade());
    AbstractSkill s1 = new GrowE(this);
    s1.upgrade();
    temp.add(s1);
    return temp;
  }
}
