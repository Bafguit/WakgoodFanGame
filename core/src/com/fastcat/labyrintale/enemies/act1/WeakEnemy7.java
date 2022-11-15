package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AttackLowE;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.GrowE;

public class WeakEnemy7 extends AbstractEnemy {

  private static final String ID = "WeakEnemy7";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 52;

  public WeakEnemy7() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 1;
    stat.debuRes = 15;
    stat.neutRes = 15;
    stat.critical = 15;
    stat.moveRes = 15;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s1 = new AttackLowE(this);
    s1.upgrade();
    s1.upgrade();
    s1.upgrade();
    s1.upgrade();
    temp.add(s1);
    AbstractSkill s = new BarrierE(this);
    s.upgrade();
    temp.add(s);
    AbstractSkill s2 = new AttackLowE(this);
    s2.upgrade();
    s2.upgrade();
    s2.upgrade();
    s2.upgrade();
    temp.add(s2);
    temp.add(new GrowE(this));
    return temp;
  }
}
