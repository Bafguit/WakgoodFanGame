package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class BossEnemy4 extends AbstractEnemy {

  private static final String ID = "BossEnemy4";
  private static final EnemyType TYPE = EnemyType.BOSS;
  private static final int HEALTH = 365;

  public BossEnemy4() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 4;
    stat.critical = 20;
    stat.debuRes = 30;
    stat.neutRes = 30;
    stat.moveRes = 30;
  }

  @Override
  public void preBattle() {
    applyStatus(new AttackStatus(2), this, 2, false);
    applyStatus(new ImmuneStatus(5), this, 5, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    /////////////////////////
    AbstractSkill s = new AdjudgeE(this);
    for (int i = 0; i < 98; i++) {
      s.upgrade();
    }
    s.disposable = true;
    temp.add(s);
    /////////////////////////
    AbstractSkill s2 = new StrikeE(this);
    for (int i = 0; i < 14; i++) {
      s2.upgrade();
    }
    temp.add(s2);
    /////////////////////////
    AbstractSkill s6 = new DecayE(this).upgrade();
    s6.upgrade();
    s6.upgrade();
    temp.add(s6);
    /////////////////////////
    temp.add(new InduceE(this));
    /////////////////////////
    AbstractSkill ss = new DoubleE(this);
    for (int i = 0; i < 6; i++) {
      ss.upgrade();
    }
    temp.add(ss);
    /////////////////////////
    temp.add(new RecapE(this));
    /////////////////////////
    temp.add(new HinderAllE(this).upgrade().upgrade().upgrade().upgrade());
    /////////////////////////
    temp.add(new RestrictE(this).upgrade());
    /////////////////////////
    AbstractSkill s4 = new GrowE(this);
    temp.add(s4);
    return temp;
  }

  @Override
  public void atEndOfTurn() {}
}
