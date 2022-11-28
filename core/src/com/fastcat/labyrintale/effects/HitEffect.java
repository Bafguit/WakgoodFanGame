package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.handlers.InputHandler;

public class HitEffect extends AbstractEffect {

  private final Sprite img;
  private final AbstractEntity e;
  private final float rw, rh;
  private float alpha = 1;

  public HitEffect(AbstractEntity e, Sprite img) {
    super(e.animX, e.animY + Gdx.graphics.getHeight() * 0.1f, 0.6f);
    this.img = new Sprite(img.getTexture());
    rw = img.getWidth() * InputHandler.scale;
    rh = img.getHeight() * InputHandler.scale;
    this.img.setBounds(x - rw * 0.5f, y - rh * 0.5f, rw, rh);
    this.e = e;
  }

  @Override
  protected void updateEffect() {
    img.setPosition(e.animX - rw * 0.5f, e.animY - rh * 0.5f + Gdx.graphics.getHeight() * 0.1f);
    float m = baseDuration * 0.5f;
    if (duration <= m) {
      alpha -= Labyrintale.tick / m;
      if (alpha < 0) alpha = 0;
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    sb.setColor(Color.WHITE);
    img.draw(sb, alpha);
  }
}
