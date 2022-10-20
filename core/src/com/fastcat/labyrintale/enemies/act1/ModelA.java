package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.GuardE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class ModelA extends AbstractEnemy {

  private static final String ID = "ModelA";
  private static final EnemyType TYPE = EnemyType.NORMAL;
  private static final int HEALTH = 36;

  public ModelA() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new BarrierE(this));
    temp.add(new GuardE(this));
    temp.add(new GrowE(this));
    temp.add(new StrikeE(this));
    return temp;
  }
}
