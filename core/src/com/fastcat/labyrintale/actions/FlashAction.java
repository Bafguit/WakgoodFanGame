package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;

public class FlashAction extends AbstractAction {

  public AbstractEntity e;
  public Sprite img;

  public FlashAction(AbstractEntity e, Sprite img) {
    super(null, 0.25f);
    this.e = e;
    this.img = img;
  }

  @Override
  protected void applySetting() {}

  @Override
  protected void updateAction() {
    if (duration == baseDuration)
      EffectHandler.add(new UpIconEffect(e.animX, e.animY + Gdx.graphics.getHeight() * 0.25f, img));
  }
}
