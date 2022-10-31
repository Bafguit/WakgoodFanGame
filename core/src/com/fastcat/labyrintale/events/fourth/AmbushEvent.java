package com.fastcat.labyrintale.events.fourth;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.BattleEventChoice;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;

public class AmbushEvent extends AbstractEvent {

  private static final String ID = "Ambush";
  private static final int SIZE = 4;

  public AmbushEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public void endBattle() {
    if (page == 1 || page == 2) setPage(3);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(new NextPageEventChoice(data.SELECT[0], this, 1, new EventCondition() {
        @Override
        public boolean condition() {
          return AbstractLabyrinth.advisor.id.equals("hiki") || AbstractLabyrinth.advisor.id.equals("callycarly");
        }

        @Override
        public String cdText() {
          return data.SELECT[1];
        }
      }));
      a.add(new NextPageEventChoice(data.SELECT[2], this, 2));
      a.add(new NextPageEventChoice(data.SELECT[3], this, 4));
    } else if (page == 1 || page == 2) {
      a.add(
          new BattleEventChoice(data.SELECT[4], AbstractLabyrinth.currentFloor.currentWay.enemies));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }

  @Override
  public void onSetPage(int p) {
    if(p == 2) {
      for(AbstractEnemy e : AbstractLabyrinth.currentFloor.currentWay.enemies.enemies) {
        e.stat.attack += 3;
      }
    }
  }
}
