package com.fastcat.labyrintale.screens.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.handlers.FileHandler.bg;

public class MapScreen extends AbstractScreen {

    public ShapeRenderer shr = new ShapeRenderer();
    public boolean isView = false;
    public boolean glow = false;
    public float alpha = 1.0f;

    public MapNodeButton[] nodes = new MapNodeButton[13];
    public MapNodeButton entryNode;
    public MapNodeButton bossNode;

    public MapScreen() {
        cType = ControlPanel.ControlType.BASIC;
        type = ScreenType.MAP;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), b = w * 0.1f;
        entryNode = nodes[0] = new MapNodeButton(currentFloor.ways[0]);
        entryNode.setPosition(b - entryNode.sWidth / 2, h * 0.85f - entryNode.sHeight / 2);
        int c = 1;
        float ww = b + w * 0.0333f;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                ww += w * (j == 3 ? 0.1f : 0.0667f);
                MapNodeButton node = new MapNodeButton(currentFloor.ways[c]);
                node.setPosition(ww - node.sWidth / 2, h * (0.85f - 0.15f * i) - node.sHeight / 2);
                nodes[c] = node;
                c++;
            }
            ww -= w * 0.0667f;
        }
        bossNode = nodes[12];
        setBg(bg.get("BG_MAP"));
    }

    @Override
    public void update() {
        if(isView && (InputHandler.cancel || InputHandler.map)) Labyrintale.removeTempScreen(this);

        for(int i = 0; i < 13; i++) {
            nodes[i].canGo = i == currentFloor.num;
            nodes[i].update();
        }

        if(!Labyrintale.fading) {
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

        shr.begin(ShapeRenderer.ShapeType.Line);
        shr.setColor(Color.DARK_GRAY);
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), sw = entryNode.sWidth / 2, sh = entryNode.sHeight / 2;

        int c = 1;
        Vector2 v1 = new Vector2(), v2 = new Vector2();
        v1.set(w * 0.1f, h * 0.85f);
        v2.set(w * 0.1333f, h * 0.85f);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                v2.add(w * (j == 3 ? 0.1f : 0.0667f), 0);
                MapNodeButton n = nodes[c];
                if(!n.way.isDone) {
                    if(n.canGo) {
                        if (n.over) {
                            shr.setColor(WHITE);
                        } else {
                            if (!mapScreen.isView && nodes[c - 1].way.isDone) shr.setColor(mapScreen.alpha, mapScreen.alpha, mapScreen.alpha, 1.0f);
                            else shr.setColor(Color.LIGHT_GRAY);
                        }
                    } else shr.setColor(Color.LIGHT_GRAY);
                } else shr.setColor(Color.DARK_GRAY);
                shr.line(v1, v2);
                v1.set(v2);
                c++;
            }
            v2.sub(w * 0.0667f, h * 0.15f);
        }
        shr.end();

        sb.begin();
        for(int i = 0; i < 13; i++) {
            nodes[i].render(sb);
        }
    }

    public void view() {
        Labyrintale.mapScreen.isView = true;
        Labyrintale.addTempScreen(Labyrintale.mapScreen);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        isView = false;
    }

    @Override
    public void dispose() {

    }
}
