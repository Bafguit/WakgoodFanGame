package com.fastcat.labyrintale.screens.event;

import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;
import static com.fastcat.labyrintale.screens.battle.BattleScreen.bc;
import static com.fastcat.labyrintale.screens.battle.BattleScreen.hbc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.PlayerView;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class EventScreen extends AbstractScreen {

  public ShapeRenderer shr = new ShapeRenderer();

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
    setBg(FileHandler.getBg().get("BG_WAY"));
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
    sb.end();
    shr.begin(ShapeRenderer.ShapeType.Filled);
    float h = Gdx.graphics.getHeight();
    for (int i = 0; i < 4; i++) {
      PlayerView tp = players[i];
      float tw = tp.sWidth, th = tp.sHeight;
      float px = tp.player.animX - tp.sWidth / 2, py = tp.player.animY - h * 0.025f;
      if (!tp.player.isDead) {
        boolean isBlock = tp.player.block > 0;
        if (isBlock) {
          shr.setColor(bc);
          shr.rect(px + tw * 0.075f, py - th * 0.01f, tw * 0.85f, th * 0.07f);
        }
        shr.setColor(hbc);
        shr.rect(px + tw * 0.1f, py, tw * 0.8f, th * 0.05f);
        shr.setColor(Color.SCARLET);
        shr.rect(
            px + tw * 0.1f,
            py,
            Math.max(tw * 0.8f * ((float) tp.player.health / (float) tp.player.maxHealth), 0),
            th * 0.05f);
      }
    }
    shr.end();
    sb.begin();
    for (int i = 0; i < 4; i++) {
      PlayerView tp = players[i];
      float tw = tp.sWidth;
      float px = tp.player.animX - tp.sWidth / 2, py = tp.player.animY - h * 0.025f;
      if (!tp.player.isDead) {
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
