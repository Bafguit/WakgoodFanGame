package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;

public class HealthBarDamageEffect extends AbstractEffect {

  private final AbstractEntity entity;
  public boolean cont = true;
  private float alpha = 0;

  public HealthBarDamageEffect(AbstractEntity e) {
    super(0, 0, 0.2f);
    entity = e;
  }

  @Override
  protected void updateEffect() {
    if (cont) {
      if (duration == baseDuration) {
        if (entity.hbEffect != null && entity.hbEffect != this) {
          entity.hbEffect.isDone = true;
          entity.hbEffect.cont = false;
        }
        entity.hbEffect = this;
        entity.animColor.set(1, 0, 0, 1);

      } else {
        alpha = Math.min(alpha + Labyrintale.tick / baseDuration, 1);
        entity.animColor.set(1, alpha, alpha, entity.animColor.a);
      }
      if (isDone) {
        entity.animColor.set(1, 1, 1, entity.animColor.a);
        entity.hbEffect = null;
      }
    }
  }

  @Override
  public void render(SpriteBatch sb) {}
}
