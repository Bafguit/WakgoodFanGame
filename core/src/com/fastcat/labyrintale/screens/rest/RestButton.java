package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;

public class RestButton extends AbstractUI implements GetSelectedPlayer, GetSelectedSlot {

  public static final int HEAL_AMOUNT = 8;

  public RestScreen sc;
  public RestType type;

  public RestButton(RestScreen s, RestType t) {
    super(FileHandler.getUi().get("WAY_SELECT"));
    sc = s;
    type = t;
  }

  @Override
  public void onClick() {
    if (type == RestType.HEAL) {
      Array<AbstractPlayer> temp = new Array<>();
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        if (p.isAlive()) temp.add(p);
      }
      AbstractPlayer[] tp = new AbstractPlayer[temp.size];
      for (int i = 0; i < temp.size; i++) {
        tp[i] = temp.get(i);
      }
      SoundHandler.playSfx("HEAL");
      for (AbstractPlayer p : tp) {
        p.heal(HEAL_AMOUNT);
      }
      sc.finishRest();
    } else if (type == RestType.UPGRADE) {
      Array<AbstractPlayer> temp = new Array<>();
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        if (p.isAlive() && p.hasSlot()) temp.add(p);
      }
      AbstractPlayer[] tp = new AbstractPlayer[temp.size];
      for (int i = 0; i < temp.size; i++) {
        tp[i] = temp.get(i);
      }
      Labyrintale.addTempScreen(new PlayerSelectScreen(tp, this));
    } else if (type == RestType.REVIVE) {
      Array<AbstractPlayer> temp = new Array<>();
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        if (!p.isAlive()) temp.add(p);
      }
      AbstractPlayer[] tp = new AbstractPlayer[temp.size];
      for (int i = 0; i < temp.size; i++) {
        tp[i] = temp.get(i);
      }
      Labyrintale.addTempScreen(new PlayerSelectScreen(tp, this));
    }
  }

  @Override
  public void playerSelected(AbstractPlayer player) {
    if (type == RestType.UPGRADE) {
      Labyrintale.addTempScreen(new SlotSelectScreen(player, this));
    } else if (type == RestType.REVIVE) {
      player.revive();
      sc.finishRest();
    } else {
      sc.finishRest();
    }
  }

  @Override
  public void slotSelected(AbstractPlayer player, int index) {
    sc.finishRest();
  }

  public enum RestType {
    HEAL,
    UPGRADE,
    DISCOVER,
    REVIVE
  }
}
