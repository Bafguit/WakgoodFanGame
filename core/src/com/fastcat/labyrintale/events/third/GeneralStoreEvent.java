package com.fastcat.labyrintale.events.third;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.*;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.items.special.GolemHead;

public class GeneralStoreEvent extends AbstractEvent {

  private static final String ID = "GeneralStore";
  private static final int SIZE = 9;

  public GeneralStoreEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(
          new SkillRewardEventChoice(
              data.SELECT[8],
              new EventCondition() {
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.gold >= 50;
                }

                @Override
                public String cdText() {
                  return data.SELECT[9];
                }

                @Override
                public void onSelect() {
                  AbstractLabyrinth.modifyGold(-50);
                }
              },
              this,
              7));
      a.add(new NextPageEventChoice(data.SELECT[11], this, 1));
      a.add(new NextPageEventChoice(data.SELECT[10], this, 8));
    } else if (page == 1) {
      a.add(
          new ItemRewardEventChoice(
              data.SELECT[6],
              AbstractItem.ItemRarity.GOLD,
              new EventCondition() {
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.gold >= 100;
                }

                @Override
                public String cdText() {
                  return data.SELECT[7];
                }

                @Override
                public void onSelect() {
                  AbstractLabyrinth.modifyGold(-100);
                }
              },
              this,
              7));
      a.add(new NextPageEventChoice(data.SELECT[11], this, 2));
      a.add(new NextPageEventChoice(data.SELECT[10], this, 8));
    } else if (page == 2) {
      a.add(
          new NextPageEventChoice(
              data.SELECT[4],
              this,
              6,
              new EventCondition() {
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.gold >= 200;
                }

                @Override
                public String cdText() {
                  return data.SELECT[5];
                }

                @Override
                public void onSelect() {
                  AbstractLabyrinth.modifyGold(-200);
                }
              }));
      a.add(new NextPageEventChoice(data.SELECT[11], this, 3));
      a.add(new NextPageEventChoice(data.SELECT[10], this, 8));
    } else if (page == 3) {
      a.add(
          new SkillGetEventChoice(
              data.SELECT[2],
              new EventCondition() { // TODO 특정 스킬
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.gold >= 300;
                }

                @Override
                public String cdText() {
                  return data.SELECT[3];
                }

                @Override
                public void onSelect() {
                  AbstractLabyrinth.modifyGold(-300);
                }
              },
              this,
              7));
      a.add(new NextPageEventChoice(data.SELECT[11], this, 4));
      a.add(new NextPageEventChoice(data.SELECT[10], this, 8));
    } else if (page == 4) {
      a.add(
          new ItemRewardEventChoice(
              data.SELECT[0],
              new GolemHead(null),
              new EventCondition() {
                @Override
                public boolean condition() {
                  return AbstractLabyrinth.gold >= 500;
                }

                @Override
                public String cdText() {
                  return data.SELECT[1];
                }

                @Override
                public void onSelect() {
                  AbstractLabyrinth.modifyGold(-500);
                }
              },
              this,
              5));
      a.add(new NextPageEventChoice(data.SELECT[10], this, 8));
    } else {
      a.add(new EndEventChoice());
    }
    return a;
  }

  @Override
  public void onSetPage(int page) {
    if (page == 6) {
      SoundHandler.playSfx("HEAL");
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        if (p.isAlive()) p.heal(p.maxHealth);
      }
    }
  }
}
