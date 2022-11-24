package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;

public class ZoomBeginAction extends AbstractAction {
  public ZoomBeginAction() {
    this(0);
  }

  public ZoomBeginAction(float duration) {
    super(null, duration);
  }

  @Override
  protected void updateAction() {
    if(isDone) {
      Labyrintale.battleScreen.zoom = true;
    }
  }
}
