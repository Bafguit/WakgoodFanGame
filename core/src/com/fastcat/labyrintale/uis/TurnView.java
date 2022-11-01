package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.battle.BattleView;

import java.util.LinkedList;

public class TurnView extends AbstractUI {

  private LinkedList<TurnIcon> icons = new LinkedList<>();

  public TurnView() {
    super(FileHandler.getUi().get("BORDER"));
  }

  public void setNewTurns(Array<BattleView> turns) {
    icons.clear();
    for (int i = 0; i < turns.size; i++) {
      TurnIcon c = new TurnIcon(turns.get(i));
      icons.add(c);
    }
  }

  @Override
  protected void updateButton() {
    if (icons.size() > 0) {
      int index = Labyrintale.battleScreen.getCurrentTurn();
      float w = Gdx.graphics.getWidth(),
          h = Gdx.graphics.getHeight(),
          wc = w * 0.5f - sWidth * 0.5f,
          wcc = w * 0.5f + sWidth * 0.5f,
          wh = h * 0.9f;
      for (int i = 0; i < index; i++) {
        TurnIcon t = icons.get(i);
        t.isMain = false;
        t.setPosition(wc - t.sWidth * (index - i), wh - t.sHeight * 0.5f);
      }

      TurnIcon tx = icons.get(index);
      tx.isMain = true;
      tx.setPosition(wc, wh - sHeight * 0.5f);

      for (int i = index + 1; i < icons.size(); i++) {
        TurnIcon t = icons.get(i);
        t.isMain = false;
        t.setPosition(wcc + t.sWidth * (i - index - 1), wh - t.sHeight * 0.5f);
      }
      for(TurnIcon c : icons) c.update();
    }
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    for (TurnIcon c : icons) {
      c.render(sb);
    }
  }

  private static class TurnIcon extends AbstractUI {

    private final Sprite bb;
    private final float ww, hh;
    public BattleView view;
    public boolean isMain;

    public TurnIcon(BattleView entity) {
      super(FileHandler.getUi().get("BORDER"));
      ww = sWidth;
      hh = sHeight;
      setScale(0.75f);
      bb = FileHandler.getUi().get("BORDER");
      clickable = false;
      this.view = entity;
      isMain = false;
    }

    @Override
    protected void updateButton() {
      if(over && view.entity.isAlive()) {
        Labyrintale.battleScreen.looking.add(view.entity);
        AbstractLabyrinth.cPanel.battlePanel.setPlayer(view.entity);
      }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
      if (enabled) {
        sb.setColor(Color.WHITE);
        if (isMain) {
          if (view != null) {
            sb.draw(view.entity.img, x, y, ww, hh);
          }
          sb.draw(bb, x, y, ww, hh);
        } else {
          if (view != null) {
            sb.draw(view.entity.img, x, y, sWidth, sHeight);
          }
          sb.draw(img, x, y, sWidth, sHeight);
        }
      }
    }
  }
}
