package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.actions.EndRoundAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class DrawPileButton extends AbstractUI {

    public DrawPileButton() {
        super(DRAW);
        setPosition(Gdx.graphics.getWidth() * 0.2f - sWidth / 2, Gdx.graphics.getHeight() * 0.35f);
        fontData = MAIN_MENU;
        text = "";
    }

    @Override
    protected void updateButton() {
        if(cPanel.battlePanel.curPlayer != null) text = Integer.toString(cPanel.battlePanel.curPlayer.drawPile.size);
    }

    @Override
    protected void onClick() {
        if(cPanel.battlePanel.curPlayer != null && !ActionHandler.isRunning) {
            addTempScreen(new DeckViewScreen(cPanel.battlePanel.curPlayer, DeckViewScreen.ViewType.DRAW));
        }
    }
}
