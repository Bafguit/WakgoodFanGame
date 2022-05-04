package com.fastcat.labyrintale.screens.charinfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.screens.deckview.BgImg;

import static com.badlogic.gdx.graphics.Color.WHITE;

public class CharInfoScreen extends AbstractScreen {

    private final BgImg bg = new BgImg();

    public AbstractPlayer player;

    public CharInfoScreen(AbstractPlayer player) {
        setPlayer(player);
        cType = Labyrintale.getBaseScreen().cType;
    }

    public void setPlayer(AbstractPlayer p) {
        player = p;
    }

    @Override
    public void update() {
        cType = Labyrintale.getBaseScreen().cType;
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);

        //spine
        player.infoSpine.render(sb);

        //health bar
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    private void resetAnimationScale() {

    }

    @Override
    public void dispose() {

    }
}
