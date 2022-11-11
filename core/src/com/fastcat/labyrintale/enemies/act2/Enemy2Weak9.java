package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class Enemy2Weak9 extends AbstractEnemy {

  private static final String ID = "Enemy2Weak9";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 15;

  public Enemy2Weak9() {
    super(ID, TYPE, HEALTH);
    stat.speed = 1;
    stat.debuRes = 5;
    stat.neutRes = 5;
    stat.critical = 10;
    stat.moveRes = 10;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new StrikeE(this));
    temp.add(new BarrierE(this));
    AbstractSkill s = new FuryE(this);
    s.upgrade();
    temp.add(s);
    return temp;
  }
}
