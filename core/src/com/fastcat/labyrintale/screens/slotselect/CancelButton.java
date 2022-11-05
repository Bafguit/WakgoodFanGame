package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class CancelButton extends AbstractUI {

  private final ConfirmSlotScreen sc;

  public CancelButton(ConfirmSlotScreen sc) {
    super(FileHandler.getUi().get("BACK"));
    setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
    fontData = MAIN_MENU;
    text = "취소";
    this.sc = sc;
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
    Labyrintale.removeTempScreen(sc);
  }
}
