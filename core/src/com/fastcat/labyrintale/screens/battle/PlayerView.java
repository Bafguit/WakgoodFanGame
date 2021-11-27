package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.players.TestPlayer;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class PlayerView extends AbstractUI {

    private Texture pImg;

    public AbstractPlayer player;
    public boolean isLooking = false;
    public boolean isOnLock = false;

    public PlayerView(AbstractPlayer.PlayerClass cls) {
        super(ENTITY_POINT);
        this.player = getWak(cls);
        pImg = PLAYER_POINT;
        showImg = false;
    }

    private static AbstractPlayer getWak(AbstractPlayer.PlayerClass cls) {
        switch (cls) {
            default:
                return new TestPlayer();
        }
    }

    @Override
    protected void updateButton() {
        if(battleScreen.currentPlayer == player) isOnLock = true;
        else isOnLock = false;

        if(isLooking || (isOnLock) || over) showImg = true;
        else showImg = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled && player != null && !player.isDead) {
            sb.setColor(Color.WHITE);
            if(showImg) sb.draw(!isLooking ? pImg : img, x, y, sWidth, sHeight);
            player.render(sb);
            //애니메이션
        }
    }

    @Override
    protected void onClick() {
        if(player != null && player.isAlive()) {
            battleScreen.setCurrentPlayer(player);
        }
    }
}
