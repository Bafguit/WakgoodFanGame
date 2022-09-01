package com.fastcat.labyrintale.screens.charinfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.StatIcon;

import static com.fastcat.labyrintale.handlers.FontHandler.INFO_HP;
import static com.fastcat.labyrintale.handlers.FontHandler.INFO_NAME;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class CharInfoScreen extends AbstractScreen {

    public static final Color hbc = new Color(0.4f, 0, 0, 1);

    private final BgImg bg = new BgImg();
    private final FontHandler.FontData fontName = INFO_NAME;
    private final FontHandler.FontData fontHp = INFO_HP;
    private final int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();

    public ShapeRenderer shr = new ShapeRenderer();
    public CharDeckIcon[] deck = new CharDeckIcon[3];
    public CharItemIcon[] item = new CharItemIcon[2];
    public StatIcon[] stats = new StatIcon[8];
    public AbstractPlayer player;

    public CharInfoScreen(AbstractPlayer player) {
        for (int i = 0; i < 3; i++) {
            CharDeckIcon b = new CharDeckIcon(null);
            b.setPosition(w * (0.5f + 0.08f * i) - b.sWidth / 2, h * 0.5f);
            deck[i] = b;
        }
        for (int i = 0; i < 2; i++) {
            CharItemIcon b = new CharItemIcon(null);
            b.setPosition(w * (0.75f + 0.08f * i) - b.sWidth / 2, h * 0.5f);
            item[i] = b;
        }
        int cnt = 0;
        for(int j = 3; j >= 0; j--) {
            for(int i = 0; i < 2; i++) {
                StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
                s.setPosition(w * (0.7f + 0.08f * i), h * (0.65f + 0.027f * j));
                stats[cnt++] = s;
            }
        }
        setPlayer(player);
        cType = Labyrintale.getBaseScreen().cType;
    }

    public void setPlayer(AbstractPlayer p) {
        player = p;
        for (int i = 0; i < 3; i++) {
            deck[i].skill = player.deck.get(i);
        }
        for (int i = 0; i < 2; i++) {
            item[i].skill = player.item[i];
        }
        for(int i = 0; i < 8; i++) {
            stats[i].entity = p;
        }
    }

    @Override
    public void update() {
        cType = Labyrintale.getBaseScreen().cType;

        for (CharDeckIcon b : deck) b.update();
        for (CharItemIcon b : item) b.update();
        for (int i = 0; i < 8; i++) {
            stats[i].update();
        }
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
        shr.rect(w * 0.47f, h * 0.68f, w * 0.14f, h * 0.03f);
        shr.setColor(Color.SCARLET.cpy());
        shr.rect(w * 0.47f, h * 0.68f, Math.max(w * 0.14f * ((float) player.health / (float) player.maxHealth), 0), h * 0.03f);
        shr.end();
        sb.begin();

        FontHandler.renderLineLeft(sb, fontName, player.name, w * 0.47f, h * 0.74f, w * 0.14f, 50);
        FontHandler.renderCenter(sb, fontHp, player.health + "/" + player.maxHealth, w * 0.47f, h * 0.697f, w * 0.14f, h * 0.03f);

        for (CharDeckIcon b : deck) b.render(sb);
        for (CharItemIcon b : item) b.render(sb);
        for(int i = 0; i < 8; i++) {
            stats[i].render(sb);
        }
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
