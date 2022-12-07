package com.fastcat.labyrintale.screens.result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.dead.DeadScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class ResultText extends AbstractUI {

  public static final String TEXT = "플레이어를 선택하세요";

  public ResultText(DeadScreen.ScreenType type) {
    super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 1000, 60);
    setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, Gdx.graphics.getHeight() * 0.875f);
    fontData = CARD_BORDER;
    text = type == DeadScreen.ScreenType.WIN ? "승리" : "멸망";
    showImg = false;
    overable = false;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (fontData != null) {
        renderCenter(sb, fontData, text, x, y, sWidth, sHeight);
      }
    }
  }
}
