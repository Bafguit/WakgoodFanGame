package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.advisorselect.AdvisorSelectScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class ConfirmButton extends AbstractUI {

  private final ConfirmSlotScreen sc;

  public ConfirmButton(ConfirmSlotScreen sc) {
    super(FileHandler.getUi().get("NEXT"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
    fontData = MAIN_MENU;
    text = "결정";
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
    sc.slotSelected(sc.player, sc.index);
    Labyrintale.removeTempScreen(sc);
  }
}
