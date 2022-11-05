package com.fastcat.labyrintale.screens.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class PrePageButton extends AbstractUI {

  private TutorialScreen sc;

  public PrePageButton(TutorialScreen sc) {
    super(FileHandler.getUi().get("BACK"));
    setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
    fontData = MAIN_MENU;
    text = "이전";
    showImg = false;
    this.sc = sc;
  }

  @Override
  protected void updateButton() {
    clickable = sc.index > 0;
    if (!over && showImg) showImg = false;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (clickable) {
      sb.setColor(Color.WHITE);
      if (showImg) sb.draw(img, x, y, sWidth, sHeight);
      renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
    }
  }

  @Override
  protected void onOver() {
    showImg = true;
  }

  @Override
  protected void onClick() {
    sc.index--;
  }
}
