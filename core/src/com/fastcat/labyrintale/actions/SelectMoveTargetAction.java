package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;

public class SelectMoveTargetAction extends AbstractAction implements GetSelectedTarget {

  public GetSelectedTarget gets;

  public SelectMoveTargetAction(GetSelectedTarget gets) {
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
        Labyrintale.wayScreen.selectTarget(gets);
      }
    } else if (!isDone) {
      if (Labyrintale.wayScreen.isSelecting) duration = 10000;
      else isDone = true;
    }
  }

  @Override
  public void onTargetSelected(AbstractEntity e) {
    isDone = true;
    Labyrintale.battleScreen.isSelecting = false;
    AbstractLabyrinth.cPanel.battlePanel.selected = null;
    gets.onTargetSelected(e);
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
