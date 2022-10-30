package com.fastcat.labyrintale.events.fourth;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.items.special.GolemHead;
import com.fastcat.labyrintale.items.special.LoyalVirus;
import com.fastcat.labyrintale.items.special.ShortBase;

public class PunkEvent extends AbstractEvent {

  private static final String ID = "Punk";
  private static final int SIZE = 3;

  public PunkEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(new ItemRewardEventChoice(data.SELECT[0], new ShortBase(null), new EventCondition() {
        @Override
        public boolean condition() {
          return AbstractLabyrinth.advisor.id.equals("dopa") || AbstractLabyrinth.advisor.id.equals("haku");
        }

        @Override
        public String cdText() {
          return data.SELECT[1];
        }
      }, this, 1));
      a.add(new ItemRewardEventChoice(data.SELECT[2], AbstractItem.ItemRarity.GOLD, new EventCondition.True(), this, 1));
      a.add(new NextPageEventChoice(data.SELECT[3], this, 2));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }

  @Override
  public void onSetPage(int page) {
    if (page == 3) {
      AbstractLabyrinth.modifyGold(150);
    }
  }
}
