package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.TurnChangeEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.screens.battle.BattleView;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;
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
      Array<AbstractEntity> turn = Labyrintale.battleScreen.getTurns();
      for(AbstractEntity e : turn) {
        if(!e.isPlayer && e.isAlive()) {
          if(e.hand[0] != null) e.hand[0].nextTurn = false;
        }
        e.pre = null;
      }
      for (AbstractEntity e : turn) {
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
      if(AbstractLabyrinth.advisor != null) AbstractLabyrinth.advisor.startOfRound();
      if(SettingHandler.setting.battleTutorial) {
        ActionHandler.bot(new TutorialAction());
      }
    }
  }
}
