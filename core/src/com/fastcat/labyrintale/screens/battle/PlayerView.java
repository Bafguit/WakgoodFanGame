package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class PlayerView extends AbstractUI {

    private Sprite pImg;

    public AbstractPlayer player;
    public boolean isLooking = false;
    public boolean isOnLock = false;

    public PlayerView(AbstractPlayer cls) {
        super(FileHandler.ui.get("ENTITY_POINT"));
        this.player = cls;
        pImg = FileHandler.ui.get("PLAYER_POINT");
        showImg = false;
    }

    @Override
    protected void updateButton() {
        isOnLock = AbstractLabyrinth.cPanel.battlePanel.curPlayer == player;
        showImg = isLooking || (isOnLock) || over;
        clickable = player.isAlive();
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
            AbstractLabyrinth.cPanel.battlePanel.setPlayer(player);
        }
    }
}
