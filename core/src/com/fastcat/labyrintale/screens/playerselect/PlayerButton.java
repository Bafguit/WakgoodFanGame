package com.fastcat.labyrintale.screens.playerselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class PlayerButton extends AbstractUI {

  public AbstractPlayer player;
  public PlayerSelectScreen select;

  public PlayerButton(AbstractPlayer adv, PlayerSelectScreen select) {
    super(FileHandler.getUi().get("BORDER_P"));
    player = adv;
    this.select = select;
  }

  @Override
  protected void updateButton() {
    if (over) AbstractLabyrinth.cPanel.infoPanel.setInfo(player);
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (over) sb.setColor(Color.WHITE);
      else sb.setColor(Color.LIGHT_GRAY);
      sb.draw(player.img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
      sb.draw(img, x, y, sWidth, sHeight);
    }
  }

  @Override
  protected void onOver() {}

  @Override
  protected void onClick() {
    select.playerSelected(player);
    Labyrintale.removeTempScreen(select);
  }
}
