package com.fastcat.labyrintale.screens.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.screens.setting.SettingScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class NextPageButton extends AbstractUI {

  private TutorialScreen sc;

  public NextPageButton(TutorialScreen sc) {
    super(FileHandler.getUi().get("NEXT"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
    fontData = MAIN_MENU;
    text = "다음";
    showImg = false;
    this.sc = sc;
  }

  @Override
  protected void updateButton() {
    if (!over && showImg) showImg = false;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    sb.setColor(Color.WHITE);
    if (showImg) sb.draw(img, x, y, sWidth, sHeight);
    renderKeywordCenter(sb, fontData, sc.index < sc.max ? text : "닫기", x, y + sHeight / 2, sWidth, sHeight);
  }

  @Override
  protected void onOver() {
    showImg = true;
  }

  @Override
  protected void onClick() {
    if(sc.index < sc.max) {
      sc.index++;
    } else {
      Labyrintale.closeTutorial();
    }
  }
}
