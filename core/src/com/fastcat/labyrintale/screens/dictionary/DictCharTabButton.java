package com.fastcat.labyrintale.screens.dictionary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.screens.setting.SettingScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class DictCharTabButton extends AbstractUI {

  public DictScreen sc;

  public DictCharTabButton(DictScreen sc) {
    super(FileHandler.getUi().get("BUTTON"));
    setPosition(Gdx.graphics.getWidth() * 0.3f - sWidth / 2, Gdx.graphics.getHeight() * 0.795f - sHeight / 2);
    fontData = BUTTON;
    text = "플레이어";
    this.sc = sc;
  }

  @Override
  protected void updateButton() {
    clickable = sc.type != DictScreen.DictType.CHAR;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (!clickable || over) sb.setColor(Color.WHITE);
      else sb.setColor(Color.LIGHT_GRAY);
      sb.draw(img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
      renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
    }
  }

  @Override
  protected void onClick() {
    sc.type = DictScreen.DictType.CHAR;
    sc.iSelected = sc.items[0];
  }
}
