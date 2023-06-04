package com.fastcat.labyrintale.screens.charinfo;

import static com.fastcat.labyrintale.handlers.FontHandler.INFO_HP;
import static com.fastcat.labyrintale.handlers.FontHandler.INFO_NAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.CloseTempScreenButton;
import com.fastcat.labyrintale.uis.StatIcon;

public class CharInfoScreen extends AbstractScreen {

    public static final Color hbc = new Color(0.4f, 0, 0, 1);

    private final BgImg bg = new BgImg();
    private final FontHandler.FontData fontName = INFO_NAME;
    private final FontHandler.FontData fontHp = INFO_HP;
    private final int w = Gdx.graphics.getWidth();

    public AbstractUI.TempUI hb = new AbstractUI.TempUI(FileHandler.getUi().get("HEALTH_BAR"));
    public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
    public ShapeRenderer shr = new ShapeRenderer();
    public CharInfoIcon cIcon;
    public CharDeckIcon[] deck = new CharDeckIcon[3];
    public CharItemIcon[] item = new CharItemIcon[2];
    public CharItemIcon passive;
    public StatIcon[] stats = new StatIcon[8];
    public AbstractEntity player;

    public CharInfoScreen(AbstractEntity player) {
        cIcon = new CharInfoIcon(0);
        cIcon.setPosition(w * 0.27f, 720 * InputHandler.scale);
        for (int i = 0; i < 3; i++) {
            CharDeckIcon b = new CharDeckIcon(null);
            b.setPosition(w * (0.4f + 0.08f * i) - b.sWidth / 2, 1440 * InputHandler.scale * 0.5f);
            deck[i] = b;
        }
        for (int i = 0; i < 2; i++) {
            CharItemIcon b = new CharItemIcon(null);
            b.setPosition(w * (0.65f + 0.08f * i) - b.sWidth / 2, 1440 * InputHandler.scale * 0.5f);
            item[i] = b;
        }
        passive = new CharItemIcon(null);
        passive.setPosition(w * 0.57f - passive.sWidth / 2, 1440 * InputHandler.scale * 0.65f);
        int cnt = 0;
        for (int j = 3; j >= 0; j--) {
            for (int i = 0; i < 2; i++) {
                StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
                s.setPosition(w * (0.62f + 0.08f * i), 1440 * InputHandler.scale * (0.65f + 0.027f * j));
                stats[cnt++] = s;
            }
        }
        setPlayer(player);
        cType = Labyrintale.getBaseScreen().cType;
    }

    public void setPlayer(AbstractEntity p) {
        player = p;
        for (int i = 0; i < 3; i++) {
            deck[i].skill = player.deck.get(i);
        }
        passive.skill = player.passive;
        for (int i = 0; i < 2; i++) {
            item[i].skill = player.item[i];
        }
        for (int i = 0; i < 8; i++) {
            stats[i].entity = p;
        }
    }

    @Override
    public void update() {
        cType = Labyrintale.getBaseScreen().cType;

        cIcon.update();
        for (CharDeckIcon b : deck) b.update();
        for (CharItemIcon b : item) b.update();
        passive.update();
        for (int i = 0; i < 8; i++) {
            stats[i].update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);

        // spine
        cIcon.render(sb);

        // button
        sb.draw(hbb, w * 0.37f, 1440 * InputHandler.scale * 0.68f, w * 0.14f, 1440 * InputHandler.scale * 0.03f);
        sb.draw(
                hb.img,
                w * 0.37f,
                1440 * InputHandler.scale * 0.68f,
                0,
                0,
                w * 0.14f,
                1440 * InputHandler.scale * 0.03f,
                Math.max(((float) player.health) / ((float) player.maxHealth), 0),
                1,
                0);

        FontHandler.renderLineLeft(sb, fontName, player.name, w * 0.37f, 1440 * InputHandler.scale * 0.74f, w * 0.14f, 50 * InputHandler.scale);
        FontHandler.renderCenter(
                sb, fontHp, player.health + "/" + player.maxHealth, w * 0.37f, 1440 * InputHandler.scale * 0.695f, w * 0.14f, 1440 * InputHandler.scale * 0.03f);

        for (CharDeckIcon b : deck) b.render(sb);
        for (CharItemIcon b : item) b.render(sb);
        passive.render(sb);
        for (int i = 0; i < 8; i++) {
            stats[i].render(sb);
        }
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
