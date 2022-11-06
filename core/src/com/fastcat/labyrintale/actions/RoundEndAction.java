package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class RoundEndAction extends AbstractAction {
  public RoundEndAction() {
    super(null, 0);
  }

  @Override
  protected void updateAction() {
    if (isDone) {
      for (AbstractEntity e : Labyrintale.battleScreen.getTurns()) {
        e.blockRemove = e.block;
        e.atEndOfRound();
        if (e.isPlayer) {
          e.passive.endOfRound();
          for (AbstractItem m : e.item) {
            if (m != null) m.endOfRound();
          }
        }
        for (AbstractStatus s : e.status) {
          s.endOfRound();
        }
      }
      if (AbstractLabyrinth.advisor != null) AbstractLabyrinth.advisor.endOfRound();
      ActionHandler.bot(new ResetTurnAction());
    }
  }
}
