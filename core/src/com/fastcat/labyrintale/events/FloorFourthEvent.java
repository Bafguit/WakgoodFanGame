package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class FloorFourthEvent extends AbstractEvent {

  private static final String ID = "FourthFloor";
  private static final int SIZE = 2;

  public FloorFourthEvent() {
    super(ID, SIZE);
    img = getImage(0);
    isEntry = true;
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) a.add(new NextPageEventChoice(data.SELECT[0], this, 1));
    else a.add(new EndEventChoice());
    return a;
  }

  @Override
  public void onSetPage(int page) {
    if (page == 1) {
      SoundHandler.playSfx("HEAL");
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        if (p.isAlive()) p.heal((int) (p.maxHealth * 0.5f));
      }
    }
  }
}
