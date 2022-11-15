package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.FourE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class Enemy3Weak5 extends AbstractEnemy {

  private static final String ID = "Enemy3Weak5";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 33;

  public Enemy3Weak5() {
    super(ID, TYPE, HEALTH);
    stat.speed = 1;
    stat.critical = 10;
    stat.debuRes = 5;
    stat.neutRes = 5;
    stat.moveRes = 45;
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
