package com.fastcat.labyrintale.players;

import static com.badlogic.gdx.graphics.Color.CHARTREUSE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.OldSword;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.viichan.ChainMail;
import com.fastcat.labyrintale.skills.player.viichan.DiaSword;

public class Viichan extends AbstractPlayer {

  private static final String ID = "viichan";
  private static final int HEALTH = 23;
  private static final Color COLOR = CHARTREUSE;

  public Viichan() {
    super(ID, HEALTH, COLOR);
    stat.speed = 3;
    stat.debuRes = 5;
    stat.neutRes = 20;
    stat.critical = 5;
    stat.moveRes = 10;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new Strike(this));
    temp.add(new ChainMail(this));
    temp.add(new DiaSword(this));
    return temp;
  }

  @Override
  public Array<AbstractItem> getStartingItem() {
    Array<AbstractItem> temp = new Array<>();
    temp.add(new PlaceHolder(this));
    temp.add(new PlaceHolder(this));
    return temp;
  }

  @Override
  public AbstractItem getPassive() {
    return new OldSword(this);
  }
}
