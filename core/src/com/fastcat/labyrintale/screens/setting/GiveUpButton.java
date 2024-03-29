package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class GiveUpButton extends AbstractUI {

    public GiveUpButton(SettingScreen sc) {
        super(FileHandler.getUi().get("SETTING_GIVEUP"));
        setPosition(1280 * InputHandler.scale - sWidth / 2, 190 * InputHandler.scale - sHeight / 2);
        screen = sc;
        isPixmap = true;
    }

    @Override
    protected void onClick() {
        SettingHandler.save();
        Labyrintale.closeSetting();
        Array<AbstractPlayer> temp = new Array<>();
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            if (p.isAlive()) temp.add(p);
        }
        for (AbstractPlayer p : temp) {
            p.die(null);
        }
    }
}
