package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.FileHandler.DISCARD;
import static com.fastcat.labyrintale.handlers.FileHandler.DRAW;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class DiscardPileButton extends AbstractUI {

    public DiscardPileButton() {
        super(DISCARD);
        setPosition(Gdx.graphics.getWidth() * 0.3f - sWidth / 2, Gdx.graphics.getHeight() * 0.35f);
        fontData = MAIN_MENU;
        text = "";
    }

    @Override
    protected void updateButton() {
        if(cPanel.battlePanel.curPlayer != null) text = Integer.toString(cPanel.battlePanel.curPlayer.discardPile.size);
    }

    @Override
    protected void onClick() {
        if(cPanel.battlePanel.curPlayer != null && !ActionHandler.isRunning) {
            addTempScreen(new DeckViewScreen(cPanel.battlePanel.curPlayer, DeckViewScreen.ViewType.DISCARD));
        }
    }
}
