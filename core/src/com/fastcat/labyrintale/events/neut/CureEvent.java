package com.fastcat.labyrintale.events.neut;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.PlayerEventChoice;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;

public class CureEvent extends AbstractEvent {

  private static final String ID = "Cure";
  private static final int SIZE = 3;

  public CureEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(new NextPageEventChoice(data.SELECT[0], this, 1));
      a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }

  @Override
  public void onSetPage(int pg) {
    if(pg == 1) {
      SoundHandler.playSfx("HEAL");
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        if(p.isAlive()) p.heal(5);
      }
    }
  }
}
