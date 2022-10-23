package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.effects.TurnChangeEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;

public class TurnChangeAction extends AbstractAction {

  boolean isEnemy;

  public TurnChangeAction(boolean isEnemy) {
    super(null, 2);
    this.isEnemy = isEnemy;
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      EffectHandler.add(new TurnChangeEffect(0));
    }
    if (isDone && !isEnemy) {
      Labyrintale.battleScreen.isEnemyTurn = false;
    }
  }
}
