package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.uis.control.ControlPanel.ControlType.BATTLE;
import static com.fastcat.labyrintale.uis.control.ControlPanel.ControlType.HIDE;

public class ControlPanel implements Disposable {

    public InfoPanel infoPanel;
    public BattlePanel battlePanel;
    public Sprite bg;
    public ControlType type;

    public ControlPanel() {
        infoPanel = new InfoPanel();
        battlePanel = new BattlePanel();
        bg = FileHandler.ui.get("CONTROL_PANEL");
        bg.setSize(bg.getWidth() * InputHandler.scale, bg.getHeight() * InputHandler.scale);
        bg.setPosition((Gdx.graphics.getWidth() - bg.getWidth()) * 0.5f, 0);
    }

    public void update() {
        type = Labyrintale.getCurScreen().cType;
        if(type != HIDE) {
            infoPanel.update();
            if (type == BATTLE) battlePanel.update();
        }
    }

    public void render(SpriteBatch sb) {
        if(type != HIDE) {
            bg.draw(sb);
            if (type == BATTLE) battlePanel.render(sb);
            infoPanel.render(sb);
        }
    }

    @Override
    public void dispose() {
        infoPanel.dispose();
        battlePanel.dispose();
    }

    public enum ControlType {
        BATTLE, BASIC, HIDE
    }
}
