package com.fastcat.labyrintale.screens.way;

import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;
import static com.fastcat.labyrintale.screens.battle.BattleScreen.bc;
import static com.fastcat.labyrintale.screens.battle.BattleScreen.hbc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;
import com.fastcat.labyrintale.uis.PlayerWayView;
import com.fastcat.labyrintale.uis.WayBgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class WayScreen extends AbstractScreen {
  private final WayBgImg bgImg;
  public ShapeRenderer shr = new ShapeRenderer();

  public Array<WaySelectButton> buttons;
  public Array<WayIcon> icons;
  public Array<WayDesc> desc;
  public AbstractWay way;
  public PlayerWayView[] players = new PlayerWayView[4];
  public GetSelectedTarget gets;
  public int wayCount;
  public boolean isSelecting = false;

  public WayScreen() {
    this(AbstractLabyrinth.currentFloor.currentWay);
  }

  public WayScreen(AbstractWay wy) {
    way = wy;
    cType = ControlPanel.ControlType.BASIC;
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    for (int i = 0; i < 4; i++) {
      PlayerWayView pv = new PlayerWayView(AbstractLabyrinth.players[i]);
      pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.49f);
      pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.515f);
      pv.player.ui = pv;
      players[i] = pv;
    }
    buttons = new Array<>();
    icons = new Array<>();
    desc = new Array<>();
    for (int i = 0; i < way.choices.length; i++) {
      AbstractChoice ch = way.choices[i];
      if (ch != null && ch.canGo) {
        float tw = w * (0.6f + 0.135f * i);

        WaySelectButton b = new WaySelectButton(this, ch);
        b.setPosition(tw - b.sWidth / 2, h * 0.65f - b.sHeight / 2);
        buttons.add(b);

        WayIcon c = new WayIcon(b, ch.img);
        c.setPosition(tw - c.sWidth / 2, h * 0.74f - c.sHeight / 2);
        icons.add(c);

        WayDesc d = new WayDesc(ch.desc);
        d.setPosition(tw - d.sWidth / 2, h * 0.62f - d.sHeight / 2);
        desc.add(d);
      }
    }
    wayCount = buttons.size;
    setBg(FileHandler.getBg().get("BG_WAY_" + AbstractLabyrinth.floorNum));
    bgImg = new WayBgImg(this);
  }

  @Override
  public void update() {
    for (int i = 0; i < 4; i++) {
      players[i].update();
    }
    for (int i = 0; i < wayCount; i++) {
      buttons.get(i).update();
      icons.get(i).update();
      desc.get(i).update();
    }
    bgImg.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    if (isSelecting) {
      for (int i = 3; i >= 0; i--) {
        PlayerWayView pv = players[i];
        if (!pv.isTarget) pv.render(sb);
      }
      bgImg.render(sb);
      for (int i = 3; i >= 0; i--) {
        PlayerWayView pv = players[i];
        if (pv.isTarget) pv.render(sb);
      }
    } else {
      for (int i = 3; i >= 0; i--) {
        players[i].render(sb);
      }
    }
    sb.end();
    shr.begin(ShapeRenderer.ShapeType.Filled);
    float h = Gdx.graphics.getHeight();
    for (int i = 0; i < 4; i++) {
      PlayerWayView tp = players[i];
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
      PlayerWayView tp = players[i];
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
    for (int i = 0; i < wayCount; i++) {
      buttons.get(i).render(sb);
      icons.get(i).render(sb);
      desc.get(i).render(sb);
    }
  }

  public void selectTarget(GetSelectedTarget gets) {
    this.gets = gets;
    isSelecting = true;
  }

  @Override
  public void show() {
    SoundHandler.addMusic("MAP", true, true).stop = false;
    if(SettingHandler.setting.wayTutorial) {
      AbstractLabyrinth.cPanel.type = ControlPanel.ControlType.BASIC;
      Labyrintale.openTutorial(TutorialScreen.TutorialType.WAY);
      SettingHandler.setting.wayTutorial = false;
      SettingHandler.save();
    }
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
