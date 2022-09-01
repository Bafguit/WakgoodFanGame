package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

public class PlayerBattleView extends AbstractUI {

    public AbstractPlayer player;
    public boolean isLooking = false;
    public boolean isOnLock = false;
    public boolean isTarget = false;
    private final Sprite pImg;

    public PlayerBattleView(AbstractPlayer cls) {
        super(FileHandler.getUi().get("ENTITY_POINT"));
        player = cls;
        player.block = 0;
        pImg = FileHandler.getUi().get("PLAYER_POINT");
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if (battleScreen.isSelecting) {
            isOnLock = AbstractLabyrinth.cPanel.battlePanel.curPlayer == player;
            clickable = player.isAlive() && isTarget;
            showImg = isLooking || isOnLock || (over && isTarget);
            if (over && clickable) {
                battleScreen.looking.add(player);
                isLooking = true;
            }
        } else {
            isOnLock = AbstractLabyrinth.cPanel.battlePanel.curPlayer == player;
            showImg = isLooking || isOnLock || over;
            clickable = player.isAlive();
        }
        mute = isOnLock;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && player != null && !player.isDead) {
            sb.setColor(Color.WHITE);
            if (showImg && battleScreen.cType == ControlPanel.ControlType.BATTLE)
                sb.draw(isLooking ? img : pImg, player.animX - sWidth / 2, player.animY - Gdx.graphics.getHeight() * 0.025f, sWidth, sHeight);
            player.render(sb);
            //애니메이션
        }
    }

    @Override
    protected void onClick() {
        if (player != null && player.isAlive()) {
            if (battleScreen.isSelecting) battleScreen.gets.onTargetSelected(player);
            else AbstractLabyrinth.cPanel.battlePanel.setPlayer(player);
        }
    }
}
