package com.fastcat.labyrintale.uis.control;

import static com.fastcat.labyrintale.uis.control.ControlPanel.ControlType.BATTLE;
import static com.fastcat.labyrintale.uis.control.ControlPanel.ControlType.HIDE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.LevelPanel;

public class ControlPanel implements Disposable {

    private static final ShapeRenderer shr = new ShapeRenderer();

    public InfoPanel infoPanel;
    public BattlePanel battlePanel;
    public LevelPanel level;
    public AbstractUI.TempUI bg;
    public ControlType type = HIDE;
    public EffectHandler effectHandler;
    public FontHandler.FontData font;

    public ControlPanel() {
        bg = new AbstractUI.TempUI(FileHandler.getUi().get("CONTROL_PANEL"));
        bg.setPosition((Gdx.graphics.getWidth() - bg.sWidth) * 0.5f, 0);
        infoPanel = new InfoPanel(this);
        battlePanel = new BattlePanel(this);
        level = new LevelPanel();
        effectHandler = EffectHandler.newInstance();
        font = FontHandler.EXP;
    }

    public void update() {
        type = Labyrintale.getCurScreen().cType;
        if (type != HIDE) {
            infoPanel.renderIcon = type != BATTLE;
            infoPanel.update();
            if (type == BATTLE) battlePanel.update();
            effectHandler.update();
            level.update();
        }
    }

    public void render(SpriteBatch sb) {
        if (type != HIDE) {
            bg.render(sb);
            if (type == BATTLE) {
                battlePanel.render(sb);
            }
            infoPanel.render(sb);
            effectHandler.render(sb);
            level.render(sb);
            /*
            sb.end();
            float f = ((float) AbstractLabyrinth.exp) / ((float) AbstractLabyrinth.maxExp);
            String p = "레벨 "
            		+ AbstractLabyrinth.level
            		+ "   "
            		+ AbstractLabyrinth.exp
            		+ " / "
            		+ AbstractLabyrinth.maxExp
            		+ " ("
            		+ (int) (f * 100)
            		+ "%)";
            shr.begin(ShapeRenderer.ShapeType.Filled);
            shr.setColor(Color.DARK_GRAY);
            float w = Gdx.graphics.getWidth(), y = Gdx.graphics.getHeight(), h = y * 0.02f;
            shr.rect(0, y - h, w, h);
            shr.setColor(Color.FOREST);
            shr.rect(0, y - h, w * f, h);
            shr.end();
            sb.begin();
            FontHandler.renderCenter(sb, font, p, 0, y - h * 0.5f, w, h);*/
        }
    }

    @Override
    public void dispose() {
        infoPanel.dispose();
        battlePanel.dispose();
    }

    public enum ControlType {
        BATTLE,
        BASIC,
        HIDE
    }
}
