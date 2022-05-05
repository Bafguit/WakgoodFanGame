package com.fastcat.labyrintale.screens.charinfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.deckview.BgImg;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_NAME;

public class CharInfoScreen extends AbstractScreen {

    public static final Color hbc = new Color(0.4f, 0, 0, 1);

    private final BgImg bg = new BgImg();
    private final FontHandler.FontData fontName = CARD_BIG_NAME;
    private final FontHandler.FontData fontHp = CARD_BIG_DESC;
    private final int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();

    public ShapeRenderer shr = new ShapeRenderer();
    public CharDeckButton[] deck = new CharDeckButton[4];
    public CharItemButton[] item = new CharItemButton[2];
    public AbstractPlayer player;

    public CharInfoScreen(AbstractPlayer player) {
        for(int i = 0; i < 4; i++) {
            CharDeckButton b = new CharDeckButton(null);
            b.setPosition(w * (0.5f + 0.08f * i) - b.sWidth / 2, h * 0.55f);
            deck[i] = b;
        }
        for(int i = 0; i < 2; i++) {
            CharItemButton b = new CharItemButton(null);
            b.setPosition(w * (0.66f + 0.08f * i) - b.sWidth / 2, h * 0.7f);
            item[i] = b;
        }
        setPlayer(player);
        cType = Labyrintale.getBaseScreen().cType;
    }

    public void setPlayer(AbstractPlayer p) {
        player = p;
        for(int i = 0; i < 4; i++) {
            deck[i].skill = player.deck.get(i);
        }
        for(int i = 0; i < 2; i++) {
            item[i].skill = player.item[i];
        }
    }

    @Override
    public void update() {
        cType = Labyrintale.getBaseScreen().cType;

        for(CharDeckButton b : deck) b.update();
        for(CharItemButton b : item) b.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);

        //spine
        player.infoSpine.render(sb);

        //health bar

        //button

        sb.end();
        shr.begin(ShapeRenderer.ShapeType.Filled);
        shr.setColor(hbc);
        shr.rect(w * 0.47f, h * 0.73f, w * 0.14f, h * 0.03f);
        shr.setColor(Color.SCARLET.cpy());
        shr.rect(w * 0.47f, h * 0.73f, Math.max(w * 0.14f * ((float) player.health / (float) player.maxHealth), 0), h * 0.03f);
        shr.end();
        sb.begin();

        FontHandler.renderLineLeft(sb, fontName, player.name, w * 0.47f, h * 0.79f, w * 0.14f, 50);
        FontHandler.renderCenter(sb, fontHp, player.health + "/" + player.maxHealth, w * 0.47f, h * 0.747f, w * 0.14f, h * 0.03f);

        for(CharDeckButton b : deck) b.render(sb);
        for(CharItemButton b : item) b.render(sb);
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
