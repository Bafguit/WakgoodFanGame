package com.fastcat.labyrintale.screens.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.badlogic.gdx.graphics.Color.LIGHT_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class MapScreen extends AbstractScreen {

    public ShapeRenderer shr = new ShapeRenderer();
    public boolean glow = false;
    public float alpha = 1.0f;

    public MapNodeButton[][] nodes = new MapNodeButton[13][3];

    public MapScreen() {
        cType = ControlPanel.ControlType.BASIC;
        type = ScreenType.MAP;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), b = w * 0.1f;
        for (int i = 0; i < 13; i++) {
            for(int j = 0; j < 3; j++) {
                AbstractChoice tw = currentFloor.ways[i].choices[j];
                if(tw != null) {
                    MapNodeButton node = new MapNodeButton(tw, i);
                    node.setPosition(b - node.sWidth / 2, h * (0.85f - 0.15f * j) - node.sHeight / 2);
                    nodes[i][j] = node;
                }
            }
            b += w * 0.0666f;
        }
        //setBg(FileHandler.getBg().get("BG_MAP"));
    }

    public static void view() {
        Labyrintale.addTempScreen(Labyrintale.mapScreen);
    }

    public void refreshFloor() {
        for (int i = 0; i < 13; i++) {
            for(int j = 0; j < 3; j++) {
                nodes[i][j].choice = currentFloor.ways[i].choices[j];
            }
        }
    }

    @Override
    public void update() {
        if (InputHandler.cancel || InputHandler.map) Labyrintale.removeTempScreen(this);
        for (int i = 1; i < 13; i++) {
            int s = currentFloor.ways[i - 1].selected;
            int s2 = currentFloor.ways[i].selected;
            for(int j = 0; j < 3; j++) {
                MapNodeButton n = nodes[i - 1][j];
                if(n != null) {
                    n.update();
                    for (int k : n.choice.linked) {
                        MapNodeButton n2 = nodes[i][k];
                        n2.canGo = n.canGo && (s == -1 || s == j) && (s2 == -1 || s2 == k);
                    }
                }
            }
        }
        nodes[12][1].update();

        if (!Labyrintale.fading) {
            if (!glow) {
                alpha -= Labyrintale.tick * 0.34f;
                if (alpha <= 0.83f) {
                    alpha = 0.83f;
                    glow = true;
                }
            } else {
                alpha += Labyrintale.tick * 0.34f;
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
                    v2.y = h * (0.85f - 0.15f * j);
                    for(int k = 0; k < len; k++) {
                        int link = n.choice.linked2.get(k);
                        v1.y = h * (0.85f - 0.15f * link);
                        MapNodeButton n2 = nodes[i - 1][link];
                        if (!n.choice.room.isDone && n2.canGo) {
                            if(currentFloor.num == i) shr.setColor(mapScreen.alpha, mapScreen.alpha, mapScreen.alpha, 1.0f);
                            else shr.setColor(LIGHT_GRAY);
                        } else shr.setColor(Color.DARK_GRAY);
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
            for(int j = 0; j < 3; j++) {
                MapNodeButton n = nodes[i][j];
                if(n != null) {
                    n.render(sb);
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
