package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class WeakEnemy6 extends AbstractEnemy {

  private static final String ID = "WeakEnemy6";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 42;

  public WeakEnemy6() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 0;
    stat.debuRes = 5;
    stat.neutRes = 20;
    stat.critical = 5;
    stat.moveRes = 5;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s = new FuryE(this).upgrade();
    s.disposable = true;
    temp.add(s);
    temp.add(new UnstoppableE(this));
    return temp;
  }
}
