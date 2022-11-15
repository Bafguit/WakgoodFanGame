package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.math.MathUtils;
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
        if(e.isPlayer && e.id.equals("wak")) {
          e.blockRemove = MathUtils.ceil(e.block * 0.34f);
        } else if(!e.hasStatus("Maintain")) {
          e.blockRemove = e.block;
        }
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
