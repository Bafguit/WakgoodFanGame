package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.uis.control.ControlPanel.ControlType.BATTLE;
import static com.fastcat.labyrintale.uis.control.ControlPanel.ControlType.HIDE;

public class ControlPanel implements Disposable {

    public InfoPanel infoPanel;
    public BattlePanel battlePanel;
    public Sprite bg;
    public Sprite bbg;
    public ControlType type = HIDE;
    public EffectHandler effectHandler;

    public ControlPanel() {
        infoPanel = new InfoPanel();
        battlePanel = new BattlePanel();
        effectHandler = new EffectHandler();
        bg = FileHandler.ui.get("CONTROL_PANEL");
        bg.setSize(bg.getWidth() * InputHandler.scale, bg.getHeight() * InputHandler.scale);
        bg.setPosition((Gdx.graphics.getWidth() - bg.getWidth()) * 0.5f, 0);
        bbg = FileHandler.ui.get("BATTLE_PANEL");
        bbg.setSize(bbg.getWidth() * InputHandler.scale, bbg.getHeight() * InputHandler.scale);
        bbg.setPosition((Gdx.graphics.getWidth() - bbg.getWidth()) * 0.5f, 0);
    }

    public void update() {
        type = Labyrintale.getCurScreen().cType;
        if(type != HIDE) {
            infoPanel.renderIcon = type != BATTLE;
            infoPanel.update();
            if (type == BATTLE) battlePanel.update();
        }
    }

    public void render(SpriteBatch sb) {
        if(type != HIDE) {
            if (type == BATTLE) {
                bbg.draw(sb);
                battlePanel.render(sb);
            }
            else {
                bg.draw(sb);
            }
            infoPanel.render(sb);
            effectHandler.render(sb);
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
