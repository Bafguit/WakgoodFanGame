package com.fastcat.labyrintale.screens.itemselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.reward.RewardScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class CancelItemButton extends AbstractUI {

  public ItemSelectScreen sc;

  public CancelItemButton(ItemSelectScreen sc) {
    super(FileHandler.getUi().get("NEXT"));
    this.sc = sc;
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.55f);
    fontData = MAIN_MENU;
    text = "넘기기";
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
