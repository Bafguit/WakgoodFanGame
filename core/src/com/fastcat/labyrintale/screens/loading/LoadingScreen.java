package com.fastcat.labyrintale.screens.loading;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

public class LoadingScreen extends AbstractScreen {

    public boolean isNew = false;
    public boolean create = true;

    public LoadingScreen() {
        cType = ControlPanel.ControlType.HIDE;
        setBg(FileHandler.getUi().get("FADE"));
        SoundHandler.fadeOutMusic("LOBBY");
    }

    public LoadingScreen(boolean b) {
        this();
        isNew = b;
    }

    @Override
    public void update() {
        if (!Labyrintale.fading && create) {
            create = false;
            if (isNew) {
                Labyrintale.labyrinth = new AbstractLabyrinth();
                Labyrintale.mapScreen = new MapScreen();
                for (int i = 0; i < Labyrintale.charSelectScreen.chars.length; i++) {
                    Labyrintale.charSelectScreen.chars[i].removeChar();
                }
                Labyrintale.charSelectScreen.nextButton.disable();
                Labyrintale.charSelectScreen.backButton.onHide();
                Labyrintale.charSelectScreen.nextButton.onHide();
                fadeOutAndChangeScreen(Labyrintale.mapScreen);
                SaveHandler.finish(true);
                SaveHandler.save();
            } else {
                Labyrintale.labyrinth = new AbstractLabyrinth(AbstractLabyrinth.RunType.SAVE);
                Labyrintale.mapScreen = new MapScreen();
                AbstractRoom tr = AbstractLabyrinth.currentFloor.currentRoom;
                if (tr.isDone) {
                    if ((tr.type == AbstractRoom.RoomType.BATTLE || tr.type == AbstractRoom.RoomType.ELITE || tr.type == AbstractRoom.RoomType.BOSS) && !tr.battleDone) {
                        battleScreen = new BattleScreen(BattleScreen.BattleType.NORMAL, true);
                        fadeOutAndChangeScreen(battleScreen);
                    } else {
                        fadeOutAndChangeScreen(Labyrintale.mapScreen);
                    }
                } else {
                    tr.enter();
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {

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
