package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import java.util.Iterator;

public class PurifyAction extends AbstractAction {

  public PurifyAction(AbstractEntity actor, Array<AbstractEntity> target) {
    super(actor, target, 0.5f);
  }

  public PurifyAction(AbstractEntity actor, AbstractEntity target) {
    super(actor, target, 0.5f);
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      if (target.size > 0) {
        for (AbstractEntity e : target) {
          if (e.isAlive()) {
            Iterator<AbstractStatus> it = e.status.iterator();
            while (it.hasNext()) {
              AbstractStatus s = it.next();
              if (s.type == AbstractStatus.StatusType.DEBUFF) {
                s.onRemove();
                it.remove();
              }
            }
          }
        }
      }
    }
  }
}
