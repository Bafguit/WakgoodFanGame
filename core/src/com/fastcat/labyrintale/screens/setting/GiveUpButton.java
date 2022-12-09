package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class GiveUpButton extends AbstractUI {

    public GiveUpButton(SettingScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.34f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f);
        fontData = BUTTON;
        text = "포기";
        screen = sc;
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
