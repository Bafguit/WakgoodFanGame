package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ControlPanel {

    public InfoPanel info;

    public ControlPanel() {
        info = new InfoPanel();
    }

    public void update() {
        info.update();
    }

    public void render(SpriteBatch sb) {
        info.render(sb);
    }

    public static class BattleGroup {

    }

    public enum ControlType {
        BATTLE, MAP, DECKVIEW, REWARD, SHOP
    }
}
