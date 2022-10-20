package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class EnemyPlaceholder extends AbstractEnemy {

  private static final String ID = "Placeholder";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 25;

  public EnemyPlaceholder() {
    super(ID, TYPE, HEALTH);
    isDead = true;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new StrikeE(this));
    temp.add(new BarrierE(this));
    return temp;
  }
}
