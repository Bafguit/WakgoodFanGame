package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;

public class ZoomEndAction extends AbstractAction {
  public ZoomEndAction() {
    this(0.5f);
  }

  public ZoomEndAction(float duration) {
    super(null, duration);
  }

  @Override
  protected void updateAction() {
    if(isDone) {
      Labyrintale.battleScreen.zoom = false;
    }
  }
}
