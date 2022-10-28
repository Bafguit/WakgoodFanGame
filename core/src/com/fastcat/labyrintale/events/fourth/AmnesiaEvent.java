package com.fastcat.labyrintale.events.fourth;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.events.choices.AmnesiaEventChoice;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.SkillRewardEventChoice;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;

public class AmnesiaEvent extends AbstractEvent {

  private static final String ID = "Amnesia";
  private static final int SIZE = 2;

  public AmnesiaEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(new AmnesiaEventChoice(data.SELECT[0], new Strike(null), new EventCondition.True(), this, 1));
      a.add(new AmnesiaEventChoice(data.SELECT[1], new Barrier(null), new EventCondition.True(), this, 1));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }
}
