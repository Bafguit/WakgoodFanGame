package com.fastcat.labyrintale.screens.healselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class CancelHealButton extends AbstractUI {

  public HealSelectScreen sc;

  public CancelHealButton(HealSelectScreen sc) {
    super(FileHandler.getUi().get("NEXT"));
    this.sc = sc;
    setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
    fontData = MAIN_MENU;
    text = "취소";
    showImg = false;
  }

  @Override
  protected void updateButton() {
    if (!over && showImg) showImg = false;
  }

  @Override
  protected void onOver() {
    showImg = true;
  }

  @Override
  protected void onClick() {
    sc.rewardDone.isRewardDone(false);
    Labyrintale.removeTempScreen(sc);
  }
}
