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
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.PlayerView;
import com.fastcat.labyrintale.uis.PlayerWayView;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class EventScreen extends AbstractScreen {

  public EventImage eventImage;
  public EventChoiceButton[] ecb;
  public AbstractEvent event;
  public int size;

  public EventScreen(AbstractEvent event) {
    cType = ControlPanel.ControlType.BASIC;
    this.event = event;
    this.event.generateChoices();
    eventImage = new EventImage(this.event);
    setPage(event.page);
    setBg(AbstractLabyrinth.curBg);
  }

  public void setPage(int page) {
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    size = event.choices[page].size;
    ecb = new EventChoiceButton[size];
    for (int i = 0; i < size; i++) {
      EventChoiceButton t = new EventChoiceButton(this.event.choices[page].get(i));
      t.setPosition(eventImage.x, h * 0.48f + t.sHeight * 1.2f * (size - 1 - i));
      ecb[i] = t;
    }
  }

  @Override
  public void update() {
    for (EventChoiceButton b : ecb) {
      b.update();
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    eventImage.render(sb);
    for (EventChoiceButton t : ecb) {
      t.render(sb);
    }
  }

  public void refresh() {}

  @Override
  public void show() {
    SoundHandler.addWay().stop = false;
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
