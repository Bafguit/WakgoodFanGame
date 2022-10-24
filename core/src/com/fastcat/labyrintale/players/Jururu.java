package com.fastcat.labyrintale.players;

import static com.badlogic.gdx.graphics.Color.MAGENTA;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.Bible;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.jururu.Pray;

public class Jururu extends AbstractPlayer {

  private static final String ID = "jururu";
  private static final int HEALTH = 20;
  private static final Color COLOR = MAGENTA;

  public Jururu() {
    super(ID, HEALTH, COLOR);
    stat.debuRes = 15;
    stat.neutRes = 15;
    stat.critical = 5;
    stat.moveRes = 5;
  }

  @Override
  public Array<AbstractSkill> getStartingDeck() {
    Array<AbstractSkill> temp = new Array<>();
    temp.add(new Strike(this));
    temp.add(new Barrier(this));
    temp.add(new Pray(this));
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
    return new Bible(this);
  }
}
