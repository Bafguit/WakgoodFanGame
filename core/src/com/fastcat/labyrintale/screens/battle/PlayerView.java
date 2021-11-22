package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.ImageHandler.BACK;
import static com.fastcat.labyrintale.handlers.ImageHandler.ENTITY_POINT;

public class PlayerView extends AbstractUI {

    private TextureAtlas atlas;
    private Skeleton skeleton;
    private AnimationState state;
    private AnimationStateData stateData;

    public AbstractPlayer player;
    public boolean isLooking = false;
    public boolean isOnLock = false;

    public PlayerView(AbstractPlayer player) {
        super(ENTITY_POINT);
        this.player = player;
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if(isOnLock) showImg = true;
        else if(!over && showImg && !isLooking) showImg = false;
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
        battleScreen.currentPlayer = player;
    }
}
