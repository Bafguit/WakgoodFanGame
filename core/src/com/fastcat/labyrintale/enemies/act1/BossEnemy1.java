package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.SetSkinAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.skills.enemy.*;

public class BossEnemy1 extends AbstractEnemy {

  private static final String ID = "BossEnemy1";
  private static final EnemyType TYPE = EnemyType.BOSS;
  private static final int HEALTH = 200;

  private int mod = 0;

  public BossEnemy1() {
    super(ID, TYPE, HEALTH);
    isRandom = false;
    stat.speed = 1;
    stat.critical = 20;
    stat.debuRes = 15;
    stat.neutRes = 15;
    stat.moveRes = 15;
    skeleton.setSkin("first");
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
  public void atEndOfTurn() {
    if (health <= (maxHealth / 2) && mod == 1) {
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
      ActionHandler.top(new SetSkinAction(this, "third", 0.5f));
    } else if (health <= ((maxHealth / 4) * 3) && mod == 0) {
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
      ActionHandler.top(new SetSkinAction(this, "second", 0.5f));
    }
  }
}
