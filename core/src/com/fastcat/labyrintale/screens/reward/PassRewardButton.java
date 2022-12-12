package com.fastcat.labyrintale.screens.reward;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;

public class PassRewardButton extends AbstractUI {

    public RewardScreen sc;

    public PassRewardButton(RewardScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        this.sc = sc;
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.4f);
        fontData = BUTTON;
        text = "계속";
    }

    @Override
    protected void onClick() {
        AbstractScreen s = Labyrintale.getBaseScreen();
        if (s == Labyrintale.battleScreen) {
            if (Labyrintale.battleScreen.type == BattleScreen.BattleType.NORMAL) {
                AbstractLabyrinth.currentFloor.currentRoom.battleDone = true;
                AbstractLabyrinth.endRoom();
            } else {
                Labyrintale.eventScreen.event.endBattle();
                SoundHandler.playSfx("CHANGE");
                Labyrintale.fadeOutAndChangeScreen(Labyrintale.eventScreen, Labyrintale.FadeType.VERTICAL);
            }
        } else if (s == Labyrintale.restScreen) {
            SoundHandler.playSfx("CHANGE");
            Labyrintale.returnToWay();
        } else {
            Labyrintale.removeTempScreen(sc);
        }
    }
}
