package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;

public class ResetTurnAction extends AbstractAction {
  public ResetTurnAction() {
    super(null, 0);
  }

  @Override
  protected void updateAction() {
    if(isDone) {
      Labyrintale.battleScreen.resetTurn();
    }
  }
}
