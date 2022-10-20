package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.ArmourStatus;

public class Enemy4Elite2 extends AbstractEnemy {

  private static final String ID = "Enemy4Elite2";
  private static final EnemyType TYPE = EnemyType.ELITE;
  private static final int HEALTH = 107;

  public Enemy4Elite2() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
  }

  @Override
  public void preBattle() {
    applyStatus(new ArmourStatus(10), this, 10, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s = new ProtectE(this);
    for (int i = 0; i < 7; i++) {
      s.upgrade();
    }
    temp.add(s);
    temp.add(new ImpregE(this));
    AbstractSkill s1 = new SlashE(this);
    for (int i = 0; i < 1; i++) {
      s1.upgrade();
    }
    temp.add(s1);
    temp.add(new GrowE(this));
    return temp;
  }
}
