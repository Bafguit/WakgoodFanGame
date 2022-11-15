package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.resultscreen.ResultScreen;

public class EndLabyrinthAction extends AbstractAction {

  private final DeadScreen.ScreenType dType;

  public EndLabyrinthAction(DeadScreen.ScreenType type) {
    super(null, 2);
    dType = type;
  }

  @Override
  protected void applySetting() {}

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      SoundHandler.fadeOutMusic("BATTLE_1");
      SoundHandler.fadeOutMusic("BATTLE_BOSS");
      ActionHandler.clear();
      Labyrintale.fadeOutAndChangeScreen(new ResultScreen(dType), 2.0f);
      SaveHandler.finish(false);
    }
  }
}
