package com.fastcat.labyrintale.events.neut;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.SkillSlotEventChoice;

public class ChaosEvent extends AbstractEvent {

  private static final String ID = "Chaos";
  private static final int SIZE = 3;

  public ChaosEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(new SkillSlotEventChoice(data.SELECT[0], new EventCondition.True(), this, 1)); // TODO
      a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }
}
