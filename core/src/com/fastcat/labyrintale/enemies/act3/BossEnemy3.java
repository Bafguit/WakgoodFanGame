package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.ImmuneStatus;
import com.fastcat.labyrintale.status.PunishStatus;

public class BossEnemy3 extends AbstractEnemy {

  private static final String ID = "BossEnemy3";
  private static final EnemyType TYPE = EnemyType.BOSS;
  private static final int HEALTH = 300;

  public BossEnemy3() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 3;
    stat.critical = 20;
    stat.debuRes = 25;
    stat.neutRes = 25;
    stat.moveRes = 25;
  }

  @Override
  public void preBattle() {
    applyStatus(new PunishStatus(2), this, 2, false);
    applyStatus(new ImmuneStatus(2), this, 2, false);
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();

    AbstractSkill s = new DecayE(this);
    for (int i = 0; i < 4; i++) {
      s.upgrade();
    }
    s.disposable = true;
    temp.add(s);

    AbstractSkill s2 = new AllTwoE(this);
    for (int i = 0; i < 4; i++) {
      s2.upgrade();
    }
    temp.add(s2);

    temp.add(new BattleE(this));

    AbstractSkill ss = new DoubleE(this);
    for (int i = 0; i < 4; i++) {
      ss.upgrade();
    }
    temp.add(ss);

    AbstractSkill s3 = new BarrierE(this);
    for (int i = 0; i < 7; i++) {
      s3.upgrade();
    }
    temp.add(s3);

    AbstractSkill s5 = new AllTwoE(this);
    for (int i = 0; i < 4; i++) {
      s5.upgrade();
    }
    temp.add(s5);

    temp.add(new CoercionE(this));

    AbstractSkill sss = new SlashE(this);
    sss.upgrade();
    sss.upgrade();
    temp.add(sss);

    AbstractSkill s4 = new GrowE(this);
    temp.add(s4);
    return temp;
  }

  @Override
  public void atEndOfRound() {}
}
