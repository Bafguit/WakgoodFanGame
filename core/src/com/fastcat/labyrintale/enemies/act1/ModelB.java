package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AmbushE;
import com.fastcat.labyrintale.skills.enemy.CorrosiveE;

public class ModelB extends AbstractEnemy {

  private static final String ID = "ModelB";
  private static final EnemyType TYPE = EnemyType.WEAK;
  private static final int HEALTH = 15;

  public ModelB() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new AmbushE(this));
    temp.add(new CorrosiveE(this));
    return temp;
  }
}
