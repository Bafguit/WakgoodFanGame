package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.battle.BattleView;

public class StartBattleAction extends AbstractAction {

  boolean isFirst;

  public StartBattleAction() {
    super(null, 0.5f);
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      AbstractLabyrinth.advisor.atBattleStart();
      for (BattleView b : battleScreen.getTurns()) {
        AbstractEntity p = b.entity;
        if (p.isPlayer) {
          for (AbstractSkill s : p.hand) {
            if (s != null) s.atBattleStart();
          }
          p.passive.atBattleStart();
          for (AbstractItem m : p.item) {
            if (m != null) m.atBattleStart();
          }
        }
      }
    }
  }
}
