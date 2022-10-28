package com.fastcat.labyrintale.events.second;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.SkillRewardEventChoice;

public class FogEvent extends AbstractEvent {

  private static final String ID = "Fog";
  private static final int SIZE = 5;

  public FogEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(
          new NextPageEventChoice(
              data.SELECT[0],
              this,
              1,
              new EventCondition() {
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.advisor.id.equals("pung");
                }

                @Override
                public String cdText() {
                  return data.SELECT[1];
                }
              }));
      a.add(new NextPageEventChoice(data.SELECT[2], this, 2));
      a.add(new NextPageEventChoice(data.SELECT[3], this, 4));
    } else if (page == 2) {
      a.add(new SkillRewardEventChoice(data.SELECT[4], new EventCondition.True(), this, 3));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }

  @Override
  public void onSetPage(int page) {
    if (page == 4) {
      // TODO
    }
  }
}
