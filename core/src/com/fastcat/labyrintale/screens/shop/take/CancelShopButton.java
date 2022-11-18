package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.itemselect.ItemSelectScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class CancelShopButton extends AbstractUI {

  public ShopTakeScreen sc;

  public CancelShopButton(ShopTakeScreen sc) {
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
    sc.isRewardDone(false);
    Labyrintale.removeTempScreen(sc);
  }
}
