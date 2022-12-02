package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fastcat.labyrintale.GifDecoder;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class GifBg extends AbstractUI {

  private final Animation<Sprite> animation;
  private float elapsed = 0;

  public GifBg(Sprite sprite, String key) {
    super(sprite);
    setPosition(0, 0);
    overable = false;
    animation = FileHandler.getGif().get(key);
  }

  public GifBg(String key) {
    this(FileHandler.getBg().get("BG_BLACK"), key);
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    elapsed += Labyrintale.tick * 0.75f;
    sb.setColor(Color.WHITE);
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    sb.draw(img, 0, 0, w, h);
    sb.draw(animation.getKeyFrame(elapsed), 0, 0, w, h);
  }
}
