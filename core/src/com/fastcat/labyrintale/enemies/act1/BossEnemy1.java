package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class BossEnemy1 extends AbstractEnemy {

  private static final String ID = "BossEnemy1";
  private static final EnemyType TYPE = EnemyType.BOSS;
  private static final int HEALTH = 158;

  private int mod = 0;

  public BossEnemy1() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    AbstractSkill s = new StrikeE(this);
    for (int i = 0; i < 5; i++) {
      s.upgrade();
    }
    temp.add(s);
    AbstractSkill s2 = new BarrierE(this);
    for (int i = 0; i < 5; i++) {
      s2.upgrade();
    }
    temp.add(s2);
    AbstractSkill s3 = new StrikeE(this);
    for (int i = 0; i < 5; i++) {
      s3.upgrade();
    }
    temp.add(s3);
    temp.add(new GrowE(this).upgrade());
    return temp;
  }

  @Override
  public void atEndOfRound() {
    if (health <= (maxHealth / 3) && mod == 1) {
      mod = 2;
      Array<AbstractSkill> temp = new Array<>();
      AbstractSkill s = new SlashE(this);
      for (int i = 0; i < 5; i++) {
        s.upgrade();
      }
      temp.add(s);
      AbstractSkill s2 = new BarrierE(this);
      for (int i = 0; i < 5; i++) {
        s2.upgrade();
      }
      temp.add(s2);
      AbstractSkill s3 = new SlashE(this);
      for (int i = 0; i < 5; i++) {
        s3.upgrade();
      }
      temp.add(s3);
      temp.add(new GrowE(this).upgrade());
      deck = temp;
      newDeck();
    } else if (health <= ((maxHealth / 3) * 2) && mod == 0) {
      mod = 1;
      Array<AbstractSkill> temp = new Array<>();
      AbstractSkill s = new DualAttackE(this);
      for (int i = 0; i < 5; i++) {
        s.upgrade();
      }
      temp.add(s);
      temp.add(new FrailStrongE(this).upgrade());
      AbstractSkill s3 = new DualAttackE(this);
      for (int i = 0; i < 5; i++) {
        s3.upgrade();
      }
      temp.add(s3);
      temp.add(new GrowE(this).upgrade());
      deck = temp;
      newDeck();
    }
  }
}
