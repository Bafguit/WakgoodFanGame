package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;

public class SelectTargetAction extends AbstractAction implements GetSelectedTarget {

  public AbstractSkill gets;

  public SelectTargetAction(AbstractSkill gets) {
    super(null, 1);
    this.gets = gets;
  }

  @Override
  protected void applySetting() {}

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      isDone = !gets.setTarget();
      if (!isDone) {
        Labyrintale.battleScreen.selectTarget(this);
        AbstractLabyrinth.cPanel.battlePanel.selected = gets;
      } else {
        gets.beforeOnTarget();
        gets.afterOnTarget();
      }
    } else if (!isDone) {
      duration = 10000;
    }
  }

  @Override
  public void onTargetSelected(AbstractEntity e) {
    isDone = true;
    Labyrintale.battleScreen.isSelecting = false;
    AbstractLabyrinth.cPanel.battlePanel.selected = null;
    if(e != null) gets.onTargetSelected(e);
    setTarget();
  }

  @Override
  public boolean setTarget() {
    for (int i = 0; i < 4; i++) {
      Labyrintale.battleScreen.players[i].isTarget = false;
      Labyrintale.battleScreen.enemies[i].isTarget = false;
    }
    return false;
  }
}
