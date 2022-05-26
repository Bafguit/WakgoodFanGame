package com.fastcat.labyrintale.screens.loading;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

public class LoadingScreen extends AbstractScreen {

    public LoadingText text;
    public boolean isNew = false;
    public boolean create = true;

    public LoadingScreen() {
        cType = ControlPanel.ControlType.HIDE;
        text = new LoadingText();
        setBg(FileHandler.ui.get("FADE"));
    }

    public LoadingScreen(boolean b) {
        this();
        isNew = b;
    }

    @Override
    public void update() {
        if(!Labyrintale.fading && create) {
            create = false;
            if (isNew) {
                Labyrintale.advisorSelectScreen.backButton.onHide();
                Labyrintale.advisorSelectScreen.nextButton.onHide();
                Labyrintale.labyrinth = new AbstractLabyrinth();
                Labyrintale.mapScreen = new MapScreen();
                Labyrintale.advisorSelectScreen.advisor.removeChar();
                for (int i = 0; i < Labyrintale.charSelectScreen.chars.length; i++) {
                    Labyrintale.charSelectScreen.chars[i].removeChar();
                }
                Labyrintale.charSelectScreen.nextButton.disable();
                Labyrintale.charSelectScreen.backButton.onHide();
                Labyrintale.charSelectScreen.nextButton.onHide();
                Labyrintale.fadeOutAndChangeScreen(Labyrintale.mapScreen);
                SaveHandler.save();
            } else {
                Labyrintale.labyrinth = new AbstractLabyrinth(AbstractLabyrinth.RunType.SAVE);
                Labyrintale.mapScreen = new MapScreen();
                AbstractRoom tr = AbstractLabyrinth.currentFloor.currentRoom;
                if(tr.isDone) {
                    if (tr.type == AbstractRoom.RoomType.BATTLE && !tr.battleDone) {
                        battleScreen = new BattleScreen(true);
                        fadeOutAndChangeScreen(battleScreen);
                    } else {
                        Labyrintale.fadeOutAndChangeScreen(Labyrintale.mapScreen);
                    }
                } else {
                    tr.enter();
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        text.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        text.dispose();
    }
}
