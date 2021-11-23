package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.players.TestPlayer;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.ImageHandler.*;
import static com.fastcat.labyrintale.handlers.ImageHandler.CHAR_SELECT;

public class PlayerView extends AbstractUI {

    public AbstractPlayer player;
    public boolean isLooking = false;
    public boolean isOnLock = false;

    public PlayerView(AbstractPlayer.PlayerClass cls) {
        super(ENTITY_POINT);
        this.player = getWak(cls);
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

        if(isLooking || (isOnLock && !battleScreen.isLooking) || over) showImg = true;
        else showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);
            //애니메이션
        }
    }

    @Override
    protected void onClick() {
        if(!player.isDead) {
            battleScreen.currentPlayer = player;
        }
    }
}
