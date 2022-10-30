package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;

public class PlayerEventChoice extends AbstractEvent.EventChoice {

  public GetSelectedPlayer event;
  public boolean onlyLive = false;

  public PlayerEventChoice(String t, GetSelectedPlayer callback) {
    super(t);
    event = callback;
  }

  public PlayerEventChoice(String t, GetSelectedPlayer callback, boolean onlyLive) {
    super(t);
    event = callback;
    this.onlyLive = onlyLive;
  }

  public PlayerEventChoice(
      String t, AbstractEvent.EventCondition condition, GetSelectedPlayer callback, boolean onlyLive) {
    super(t, condition);
    event = callback;
    this.onlyLive = onlyLive;
  }

  @Override
  protected void onSelect() {
    if(onlyLive) {
      Array<AbstractPlayer> temp = new Array<>();
      for(AbstractPlayer p : AbstractLabyrinth.players) {
        if(p.isAlive()) temp.add(p);
      }
      if(temp.size > 0) Labyrintale.addTempScreen(new PlayerSelectScreen(temp.items, event));
    }
    else Labyrintale.addTempScreen(new PlayerSelectScreen(event));
  }
}
