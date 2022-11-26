package com.fastcat.labyrintale.screens.event;

import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;
import static com.fastcat.labyrintale.screens.battle.BattleScreen.bc;
import static com.fastcat.labyrintale.screens.battle.BattleScreen.hbc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.PlayerView;
import com.fastcat.labyrintale.uis.PlayerWayView;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class EventScreen extends AbstractScreen {

  public ShapeRenderer shr = new ShapeRenderer();

  public Sprite hb = FileHandler.getUi().get("HEALTH_BAR");
  public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
  public EventImage eventImage;
  public EventChoiceButton[] ecb;
  public PlayerView[] players = new PlayerView[4];
  public AbstractEvent event;
  public int size;

  public EventScreen(AbstractEvent event) {
    cType = ControlPanel.ControlType.BASIC;
    this.event = event;
    this.event.generateChoices();
    eventImage = new EventImage(this.event);
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    for (int i = 0; i < 4; i++) {
      PlayerView pv = new PlayerView(AbstractLabyrinth.players[i]);
      pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.49f);
      pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.515f);
      pv.player.ui = pv;
      players[i] = pv;
    }
    setPage(event.page);
    setBg(AbstractLabyrinth.curBg);
  }

  public void setPage(int page) {
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    size = event.choices[page].size;
    ecb = new EventChoiceButton[size];
    for (int i = 0; i < size; i++) {
      EventChoiceButton t = new EventChoiceButton(this.event.choices[page].get(i));
      t.setPosition(w * 0.5f, h * 0.48f + t.sHeight * 1.2f * (size - 1 - i));
      ecb[i] = t;
    }
  }

  @Override
  public void update() {
    for (int i = 0; i < 4; i++) {
      players[i].update();
    }
    for (EventChoiceButton b : ecb) {
      b.update();
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    eventImage.render(sb); // TODO 변경 요망
    for (int i = 3; i >= 0; i--) {
      players[i].render(sb);
    }
    float h = Gdx.graphics.getHeight();
    for (int i = 0; i < 4; i++) {
      PlayerView tp = players[i];
      float tw = tp.sWidth, th = tp.sHeight;
      float px = tp.player.animX - tp.sWidth / 2, py = tp.player.animY - h * 0.025f;
      if (!tp.player.isDead) {
        sb.draw(hbb, px + tw * 0.1f, py, tw * 0.8f, th * 0.05f);
        sb.draw(hb, px + tw * 0.1f, py, 0, 0, tw * 0.8f, th * 0.05f,
                Math.max(((float) tp.player.health) / ((float) tp.player.maxHealth), 0), 1, 0);
        renderCenter(
                sb,
                HP,
                tp.player.health + "/" + tp.player.maxHealth,
                px,
                py + tp.sHeight * 0.06f / 2,
                tw,
                tp.sHeight * 0.05f);
      }
    }
    for (EventChoiceButton t : ecb) {
      t.render(sb);
    }
  }

  public void refresh() {}

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
