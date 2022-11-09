package com.fastcat.labyrintale.events.fourth;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.PlayerEventChoice;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;

public class RevelationEvent extends AbstractEvent implements GetSelectedPlayer {

  private static final String ID = "Revelation";
  private static final int SIZE = 4;

  public RevelationEvent() {
    super(ID, SIZE);
    img = getImage(0);
  }

  @Override
  public Array<EventChoice> getChoices(int page) {
    Array<EventChoice> a = new Array<>();
    if (page == 0) {
      a.add(new NextPageEventChoice(data.SELECT[0], this, 1));
      a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
      a.add(new PlayerEventChoice(data.SELECT[2], new EventCondition() {
        @Override
        public boolean condition() {
          int cnt = 0;
          for(AbstractPlayer p : AbstractLabyrinth.players) {
            if(p.isAlive()) cnt++;
          }
          return cnt > 1;
        }

        @Override
        public String cdText() {
          return data.SELECT[3];
        }
      }, this, true));
    }
    else a.add(new EndEventChoice());
    return a;
  }

  @Override
  public void onSetPage(int page) {
    if (page == 1) {
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        p.stat.attack += 3;
        p.health = 1;
      }
    } else if (page == 2) {
      int min = AbstractLabyrinth.players[0].maxHealth;
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        p.stat.spell += 3;
        if(p.maxHealth < min) min = p.maxHealth;
      }
      for(AbstractPlayer p : AbstractLabyrinth.players) {
        p.setMaxHealth(min, false);
      }
    }
  }

  @Override
  public void playerSelected(AbstractPlayer player) {
    setPage(3);
    for (AbstractPlayer p : AbstractLabyrinth.players) {
      p.stat.neutRes += 3;
      p.stat.debuRes += 3;
      p.stat.moveRes += 3;
    }
    player.takeDamage(new AbstractEntity.DamageInfo(null, 50, AbstractEntity.DamageType.LOSE));
  }
}
