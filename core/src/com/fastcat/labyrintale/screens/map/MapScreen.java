package com.fastcat.labyrintale.screens.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

public class MapScreen extends AbstractScreen {

    public ShapeRenderer shr = new ShapeRenderer();
    public boolean isView = false;
    public boolean glow = false;
    public float alpha = 1.0f;

    public MapNodeButton[] nodes = new MapNodeButton[13];
    public MapNodeButton entryNode;
    public MapNodeButton bossNode;

    public MapScreen() {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        nodes[0] = new MapNodeButton(currentFloor.entryRoom);
        nodes[0].setPosition(w * 0.1f - entryNode.sWidth / 2, h * 0.5f - entryNode.sHeight / 2);
        for(int i = 1; i < 12; i++) {
            MapNodeButton node = new MapNodeButton(currentFloor.rooms[i]);
            node.setPosition(w * 0.1f * (i + 3) - node.sWidth / 2, h * 0.5f - node.sHeight / 2);
            nodes[i] = node;
        }
        nodes[12] = new MapNodeButton(currentFloor.bossRoom);
        nodes[12].setPosition(w * 0.9f - bossNode.sWidth / 2, h / 2 - bossNode.sHeight / 2);
        setBg(FileHandler.BG_MAP);
    }

    @Override
    public void update() {
        for(int i = 0; i < 12; i++) {
            nodes[i].canGo = i <= currentFloor.num;
            nodes[i].update();
        }
        nodes[12].canGo = currentFloor.canBoss;
        nodes[12].update();

        if(!Labyrintale.fading) {
            if (!glow) {
                alpha -= Gdx.graphics.getDeltaTime() * 0.34f;
                if (alpha <= 0.83f) {
                    alpha = 0.83f;
                    glow = true;
                }
            } else {
                alpha += Gdx.graphics.getDeltaTime() * 0.34f;
                if (alpha >= 1) {
                    alpha = 1;
                    glow = false;
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        /*sb.end();

        shr.begin(ShapeRenderer.ShapeType.Line);
        shr.setColor(Color.DARK_GRAY);
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        switch (currentFloor.nt) {
            case UP:
                shr.line(w * 0.2f + entryNode.sWidth / 2, h / 2, w * 0.3f - entryNode.sWidth / 2, h * 0.6f);
                for(int i = 1; i < 5; i++) {
                    MapNodeButton temp = upNodes[i];
                    if(upNodes[i - 1].room.isDone && !temp.room.isDone && !isView) {
                        if(temp.over) shr.setColor(Color.WHITE);
                        else shr.setColor(alpha, alpha, alpha, alpha);
                    }
                    else if(!temp.room.isDone) shr.setColor(Color.LIGHT_GRAY);
                    shr.line(w * 0.1f * (i + 2) + entryNode.sWidth / 2, h * 0.6f, w * 0.1f * (i + 3) - entryNode.sWidth / 2, h * 0.6f);
                }
                if(!bossNode.room.isDone) {
                    if(bossNode.canGo && !isView) {
                        if(bossNode.over) shr.setColor(Color.WHITE);
                        else shr.setColor(alpha, alpha, alpha, alpha);
                    }
                    else shr.setColor(Color.LIGHT_GRAY);
                }
                shr.line(w * 0.7f + entryNode.sWidth / 2, h * 0.6f, w * 0.8f - entryNode.sWidth / 2, h / 2);
                break;
            case DOWN:
                shr.line(w * 0.2f + entryNode.sWidth / 2, h / 2, w * 0.3f - entryNode.sWidth / 2, h * 0.4f);
                for(int i = 1; i < 5; i++) {
                    MapNodeButton temp = downNodes[i];
                    if(downNodes[i - 1].room.isDone && !temp.room.isDone && !isView) {
                        if(temp.over) shr.setColor(Color.WHITE);
                        else shr.setColor(alpha, alpha, alpha, alpha);
                    }
                    else if(!temp.room.isDone) shr.setColor(Color.LIGHT_GRAY);
                    shr.line(w * 0.1f * (i + 2) + entryNode.sWidth / 2, h * 0.4f, w * 0.1f * (i + 3) - entryNode.sWidth / 2, h * 0.4f);
                }
                if(!bossNode.room.isDone) {
                    if(bossNode.canGo && !isView) {
                        if(bossNode.over) shr.setColor(Color.WHITE);
                        else shr.setColor(alpha, alpha, alpha, alpha);
                    }
                    else shr.setColor(Color.LIGHT_GRAY);
                }
                shr.line(w * 0.7f + entryNode.sWidth / 2, h * 0.4f, w * 0.8f - entryNode.sWidth / 2, h / 2);
                break;
            default:
                if(upNodes[0].over && !isView) shr.setColor(Color.WHITE);
                else shr.setColor(alpha, alpha, alpha, alpha);
                shr.line(w * 0.2f + entryNode.sWidth / 2, h / 2, w * 0.3f - entryNode.sWidth / 2, h * 0.6f);
                if(downNodes[0].over && !isView) shr.setColor(Color.WHITE);
                else shr.setColor(alpha, alpha, alpha, alpha);
                shr.line(w * 0.2f + entryNode.sWidth / 2, h / 2, w * 0.3f - entryNode.sWidth / 2, h * 0.4f);
        }
        shr.end();

        sb.begin();*/
        for(int i = 0; i < 13; i++) {
            nodes[i].render(sb);
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
