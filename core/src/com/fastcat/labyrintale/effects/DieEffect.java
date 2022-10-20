package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import java.util.LinkedList;

public class DieEffect extends AbstractEffect {

  private final AbstractEntity actor;

  public DieEffect(AbstractEntity e) {
    super(0, 0, 1.3f);
    actor = e;
  }

  @Override
  protected void updateEffect() {
    if (actor != null) {
      if (duration == baseDuration) {
        AnimationState.TrackEntry e = actor.state.setAnimation(0, "die", false);
        e.setTimeScale(baseDuration / 2);
        actor.infoSpine.setAnimation("die");
      }
      if (isDone) {
        actor.isDead = true;
        actor.isDie = false;
        actor.status = new LinkedList<>();
      }
    }
  }

  @Override
  public void render(SpriteBatch sb) {}
}
