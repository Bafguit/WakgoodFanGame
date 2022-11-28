package com.fastcat.labyrintale.effects;

import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectPublicText;

public class UpDamageEffect extends AbstractEffect {

  private final EffectPublicText text;

  public UpDamageEffect(float x, float y, int damage, Color color, boolean isNegative) {
    super(x, y, 1);
    text = new EffectPublicText(FileHandler.getUi().get("MENU_SELECT"), 300, 60);
    text.fontData = new FontHandler.FontData(MEDIUM, 53, color, true, true);
    damage = Math.max(damage, 0);
    text.text = damage != 0 ? isNegative ? "-" + damage : "+" + damage : "0";
    text.setPosition(x - text.sWidth / 2, y - text.sHeight / 2);
  }

  @Override
  protected void updateEffect() {
    if (duration != baseDuration) {
      if (text.fontData != null) {
        if(duration <= baseDuration / 2) {
          text.fontData.alpha -= Labyrintale.tick / baseDuration * 2;
          if (text.fontData.alpha < 0) text.fontData.alpha = 0;
        }
        text.y += Labyrintale.tick * 100;
      }
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    sb.setColor(Color.WHITE);
    text.render(sb);
  }

  @Override
  public void dispose() {
    text.fontData.dispose();
  }
}
