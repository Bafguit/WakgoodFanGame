package com.fastcat.labyrintale.events.second;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.BetEventChoice;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;

public class BettingEvent extends AbstractEvent {

  private static final String ID = "Betting";
  private static final int SIZE = 5;

  public BettingEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(
          new BetEventChoice(
              data.SELECT[0],
              this,
              70,
              1,
              3,
              new EventCondition() {
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.gold >= 50;
                }

                @Override
                public String cdText() {
                  return data.SELECT[2];
                }

                @Override
                public void onSelect() {
                  AbstractLabyrinth.modifyGold(-50);
                }
              }));
      a.add(
          new BetEventChoice(
              data.SELECT[1],
              this,
              30,
              2,
              3,
              new EventCondition() {
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.gold >= 50;
                }

                @Override
                public String cdText() {
                  return data.SELECT[2];
                }

                @Override
                public void onSelect() {
                  AbstractLabyrinth.modifyGold(-50);
                }
              }));
      a.add(new NextPageEventChoice(data.SELECT[3], this, 4));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }

  @Override
  public void onSetPage(int page) {
    if (page == 1) {
      AbstractLabyrinth.modifyGold(100);
    } else if (page == 2) {
      AbstractLabyrinth.modifyGold(300);
    }
  }
}
