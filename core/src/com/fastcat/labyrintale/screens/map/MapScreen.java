package com.fastcat.labyrintale.screens.map;

import static com.badlogic.gdx.graphics.Color.*;
import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class MapScreen extends AbstractScreen {

  public ShapeRenderer shr = new ShapeRenderer();
  public boolean glow = false;
  public float alpha = 1.0f;

  public MapNodeButton[][] nodes = new MapNodeButton[13][3];

  public MapScreen() {
    cType = Labyrintale.getBaseScreen().cType;
    type = ScreenType.MAP;
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), b = w * 0.1f;
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 3; j++) {
        AbstractChoice tw = currentFloor.ways[i].choices[j];
        if (tw != null) {
          MapNodeButton node = new MapNodeButton(tw, i);
          node.setPosition(b - node.sWidth / 2, h * (0.8f - 0.15f * j) - node.sHeight / 2);
          nodes[i][j] = node;
        }
      }
      b += w * 0.0666f;
    }
    setBg(AbstractLabyrinth.curBg);
  }

  public static void view() {
    Labyrintale.addTempScreen(Labyrintale.mapScreen);
  }

  public static void remove() {
    Labyrintale.removeTempScreen(Labyrintale.mapScreen);
  }

  public void refreshFloor() {
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), b = w * 0.1f;
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 3; j++) {
        AbstractChoice c = currentFloor.ways[i].choices[j];
        MapNodeButton n = null;
        if (c != null) {
          n = new MapNodeButton(c, j);
          n.setPosition(b - n.sWidth / 2, h * (0.8f - 0.15f * j) - n.sHeight / 2);
        }
        nodes[i][j] = n;
      }
      b += w * 0.0666f;
    }
  }

  @Override
  public void update() {
    cType = Labyrintale.getBaseScreen().cType;
    if (InputHandler.cancel || InputHandler.map) Labyrintale.removeTempScreen(this);
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 3; j++) {
        MapNodeButton n = nodes[i][j];
        if (n != null) {
          n.update();
        }
      }
    }

    if (!Labyrintale.fading) {
      if (!glow) {
        alpha -= Labyrintale.tick * 0.75f;
        if (alpha <= 0.5f) {
          alpha = 0.5f;
          glow = true;
        }
      } else {
        alpha += Labyrintale.tick * 0.75f;
        if (alpha >= 1) {
          alpha = 1;
          glow = false;
        }
      }
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    sb.end();

    shr.begin(ShapeRenderer.ShapeType.Filled);
    shr.setColor(Color.DARK_GRAY);
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();

    Vector2 v1 = new Vector2(w * 0.1f, 0), v2 = new Vector2(w * 0.1666f, 0);
    for (int i = 1; i < 13; i++) {
      for (int j = 0; j < 3; j++) {
        MapNodeButton n = nodes[i][j];
        if (n != null) {
          int len = n.choice.linked2.size();
          v2.y = h * (0.8f - 0.15f * j);
          for (int k = 0; k < len; k++) {
            int link = n.choice.linked2.get(k);
            v1.y = h * (0.8f - 0.15f * link);
            MapNodeButton n2 = nodes[i - 1][link];
            if (n.choice.canGo && n2.choice.canGo) {
              if (!n.choice.room.isDone && currentFloor.num == i) {
                if (currentFloor.ways[i - 1].selected == -1
                    || currentFloor.ways[i - 1].selected == link)
                  shr.setColor(mapScreen.alpha, mapScreen.alpha, 0, 1.0f);
                else shr.setColor(DARK_GRAY);
              } else shr.setColor(LIGHT_GRAY);
            } else shr.setColor(DARK_GRAY);
            shr.rectLine(v1, v2, 5 * InputHandler.scale);
          }
        }
      }
      v1.x += w * 0.0666f;
      v2.x += w * 0.0666f;
    }
    shr.end();

    sb.begin();
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 3; j++) {
        MapNodeButton n = nodes[i][j];
        if (n != null) {
          n.render(sb);
        }
      }
    }
  }

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
