package com.fastcat.labyrintale.screens.dead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class DeadScreen extends AbstractScreen {

    public DeadText logo;
    public MainButton mainButton;
    public FontHandler.FontData font;
    public int m, s;

    public DeadScreen(ScreenType type) {
        logo = new DeadText();
        if (type == ScreenType.DEAD) {
            logo.text = "개같이 멸망";
            setBg(FileHandler.getBg().get("BG_DEAD"));
        } else {
            logo.text = "해냈다 해냈어!";
            setBg(FileHandler.getBg().get("BG_WIN"));
        }
        mainButton = new MainButton();
        cType = ControlPanel.ControlType.HIDE;
        font = FontHandler.MAIN_MENU_SHADOW;
        m = AbstractLabyrinth.minute;
        s = AbstractLabyrinth.second;
    }

    @Override
    public void update() {
        mainButton.update();
        logo.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        mainButton.render(sb);
        logo.render(sb);
        FontHandler.renderCenter(
                sb,
                font,
                "소요 시간: " + m + "분 " + s + "초",
                Gdx.graphics.getWidth() * 0.5f,
                720 * InputHandler.scale);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    public enum ScreenType {
        DEAD,
        WIN
    }
}
