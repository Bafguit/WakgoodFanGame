package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.charInfoScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;

public class PlayerBigIcon extends AbstractUI {

  public AbstractEntity p;

  public PlayerBigIcon(AbstractPlayer p) {
    super(FileHandler.getUi().get("BORDER_V"));
    setScale(1.16f);
    this.p = p;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled && p != null) {
      sb.setColor(Color.WHITE);
      sb.draw(p.imgPanel, x, y, sWidth, sHeight);
      sb.draw(img, x, y, sWidth, sHeight);
    }
  }

  @Override
  protected void updateButton() {
    if (over && p != null) {
      AbstractLabyrinth.cPanel.infoPanel.setInfo(p);
    }
  }

  public void setPlayer(AbstractEntity p) {
    this.p = p;
  }

  @Override
  protected void onOver() {}

  @Override
  protected void onClick() {
    if(p.isPlayer) {
      AbstractPlayer player = (AbstractPlayer) p;
      if (charInfoScreen == null) {
        charInfoScreen = new CharInfoScreen(player);
        Labyrintale.addTempScreen(charInfoScreen);
      } else if (charInfoScreen.player == player) {
        if (Labyrintale.getCurScreen() != charInfoScreen) {
          Labyrintale.removeTempScreen(charInfoScreen);
          Labyrintale.addTempScreen(charInfoScreen);
        } else {
          Labyrintale.removeTempScreen(charInfoScreen);
          charInfoScreen = null;
        }
      } else {
        if (Labyrintale.getCurScreen() != charInfoScreen) {
          Labyrintale.removeTempScreen(charInfoScreen);
          charInfoScreen.setPlayer(player);
          Labyrintale.addTempScreen(charInfoScreen);
        } else charInfoScreen.setPlayer(player);
      }
    }
  }
}
