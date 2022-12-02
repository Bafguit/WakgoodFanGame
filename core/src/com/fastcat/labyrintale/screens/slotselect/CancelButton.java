package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

public class CancelButton extends AbstractUI {

  private final ConfirmSlotScreen sc;

  public CancelButton(ConfirmSlotScreen sc) {
    super(FileHandler.getUi().get("BUTTON"));
    setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.4f);
    fontData = BUTTON;
    text = "취소";
    this.sc = sc;
  }

  @Override
  protected void onClick() {
    Labyrintale.removeTempScreen(sc);
  }
}
