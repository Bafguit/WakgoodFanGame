package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.TurnChangeEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.screens.battle.BattleView;
import com.fastcat.labyrintale.uis.TurnEffectText;

public class RoundStartAction extends AbstractAction {

  private final int round;

  public RoundStartAction(int round) {
    super(null, 2);
    this.round = round;
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      EffectHandler.add(new TurnChangeEffect(round));
      AbstractLabyrinth.energy =
          Math.min(
              AbstractLabyrinth.energy + AbstractLabyrinth.charge, AbstractLabyrinth.MAX_ENERGY);
      for (BattleView b : Labyrintale.battleScreen.getTurns()) {
        AbstractEntity e = b.entity;
        e.blockRemove = e.block;
        if (e.isPlayer) {
          e.passive.startOfRound();
          for (AbstractItem m : e.item) {
            if (m != null) m.startOfRound();
          }
        }
        for (AbstractStatus s : e.status) {
          s.startOfRound();
        }
      }
    }
  }
}
