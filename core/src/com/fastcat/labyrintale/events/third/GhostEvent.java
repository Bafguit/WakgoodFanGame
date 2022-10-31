package com.fastcat.labyrintale.events.third;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;

public class GhostEvent extends AbstractEvent {

  private static final String ID = "Ghost";
  private static final int SIZE = 3;

  public GhostEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(new NextPageEventChoice(data.SELECT[0], this, 1, new EventCondition() {
        @Override
        public boolean condition() {
          return AbstractLabyrinth.advisor.id.equals("duksu");
        }

        @Override
        public String cdText() {
          return data.SELECT[1];
        }
      }));
      a.add(new NextPageEventChoice(data.SELECT[2], this, 2));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }

  @Override
  public void onSetPage(int page) {
    if (page == 2) {
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        p.modifyMaxHealth(-3);
      }
    }
  }
}
