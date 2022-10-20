package com.fastcat.labyrintale.events.neut;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;

public class GoldEvent extends AbstractEvent {

  private static final String ID = "Gold";
  private static final int SIZE = 3;

  public GoldEvent() {
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
  public void onSetPage(int page) {
    if (page == 1) AbstractLabyrinth.modifyGold(100);
  }
}
