package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.GifBg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class RestScreen extends AbstractScreen {

  public int count;
  public RestButton[] buttons;
  public RestDesc[] desc;
  public AbstractUI.TempUI[] chars = new AbstractUI.TempUI[4];
  public RestEndButton end;
  public final GifBg fire, light;
  public int alive = 0;

  public RestScreen() {
    light = new GifBg("FIRE_LIGHT");
    fire = new GifBg(FileHandler.getBg().get("BG_REST_BAG"), "FIRE");
    cType = ControlPanel.ControlType.BASIC;
    count = 2;
    end = new RestEndButton();
    end.disable();
    for (AbstractPlayer p : AbstractLabyrinth.players) {
      if (!p.isAlive()) {
        count++;
        break;
      }
    }
    for(int i = 0; i < 4; i++) {
      if(AbstractLabyrinth.players[i].isAlive()) alive++;
      chars[i] = new AbstractUI.TempUI(FileHandler.getUi().get("CAMP"));
    }

    chars[0].setPosition(670 * scale, 600 * scale);
    chars[0].img.setFlip(false, false);
    chars[1].setPosition(920 * scale, 654 * scale);
    chars[1].img.setFlip(false, false);
    chars[2].setPosition(1224 * scale, 654 * scale);
    chars[2].img.setFlip(true, false);
    chars[3].setPosition(1474 * scale, 600 * scale);
    chars[3].img.setFlip(true, false);

    for(int i = 0; i < 4; i++) {
      AbstractPlayer p = AbstractLabyrinth.players[i];
      chars[i].img = alive > 2 ? p.camp : p.upset;
    }

    buttons = new RestButton[count];
    desc = new RestDesc[count];

    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    float tw = w / 6, tww = w / 3;
    int cnt = 0;

    RestButton b = buttons[cnt] = new RestButton(this, RestButton.RestType.HEAL);
    b.setPosition(tw - b.sWidth / 2, h * 0.73f - b.sHeight / 2);

    RestDesc d = desc[cnt] = new RestDesc("휴식");
    d.setPosition(tw - d.sWidth / 2, b.y);

    if (count > 2) {
      tw += tww;
      cnt++;

      RestButton b3 = buttons[cnt] = new RestButton(this, RestButton.RestType.REVIVE);
      b3.setPosition(tw - b3.sWidth / 2, h * 0.83f - b3.sHeight / 2);

      RestDesc d3 = desc[cnt] = new RestDesc("소생");
      d3.setPosition(tw - d3.sWidth / 2, b3.y);
    } else {
      tw += tww;
    }

    tw += tww;
    cnt++;

    RestButton b2 = buttons[cnt] = new RestButton(this, RestButton.RestType.UPGRADE);
    b2.setPosition(tw - b2.sWidth / 2, h * 0.73f - b2.sHeight / 2);

    RestDesc d2 = desc[cnt] = new RestDesc("단련");
    d2.setPosition(tw - d2.sWidth / 2, b2.y);
    setBg(AbstractLabyrinth.curBg);
  }

  @Override
  public void update() {
    for(int i = 0; i < 4; i++) {
      AbstractPlayer p = AbstractLabyrinth.players[i];
      AbstractUI.TempUI u = chars[i];
      u.img = alive > 2 ? p.camp : p.upset;
      u.showImg = p.isAlive();
      u.img.setFlip(i > 1, false);
      u.update();
    }
    for (int i = 0; i < count; i++) {
      buttons[i].update();
      desc[i].update();
    }
    end.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    light.render(sb);
    for(int i = 0; i < 4; i++) {
      chars[i].render(sb);
    }
    fire.render(sb);
    for (int i = 0; i < count; i++) {
      buttons[i].render(sb);
      desc[i].render(sb);
    }
    end.render(sb);
  }

  public void finishRest() {
    for (int i = 0; i < count; i++) {
      buttons[i].disable();
      desc[i].disable();
    }
    end.enable();
  }

  @Override
  public void show() {
    SoundHandler.addWay().stop = false;
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
