package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;

public class TutorialAction extends AbstractAction {

  public TutorialAction() {
    super(null, 2f);
    baseDuration = duration = 1;
  }

  @Override
  protected void updateAction() {
    if(isDone) {
      Labyrintale.openTutorial(TutorialScreen.TutorialType.BATTLE);
      SettingHandler.setting.battleTutorial = false;
      SettingHandler.save();
    }
  }
}
