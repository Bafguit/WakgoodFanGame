package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.deckview.BgImg;

public class ShopTakeScreen extends AbstractScreen {

    private final BgImg bg = new BgImg();
    public TakeType type;
    public AbstractSkill skill;
    public AbstractItem item;
    public ShopItemIcon shopItem;
    public CharDeckButton[] deck;
    public CharItemButton[][] items;
    public int count;

    public ShopTakeScreen(AbstractSkill skill) {
        this.skill = skill;
        type = TakeType.SKILL;
        deck = new CharDeckButton[4];
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < 4; i++) {
            CharDeckButton c = new CharDeckButton(this, i, this.skill);
            c.setPosition(w * (0.35f + 0.1f * i) - c.sWidth * 0.5f, h * 0.775f - c.sHeight * 0.5f);
            deck[i] = c;
        }
        shopItem = new ShopItemIcon(this);
        shopItem.setPosition(w * 0.5f - shopItem.sWidth * 0.5f, h * 0.575f - shopItem.sHeight * 0.5f);
    }

    public ShopTakeScreen(AbstractItem item) {
        this.item = item;
        type = TakeType.ITEM;
        Array<AbstractPlayer> p = new Array<>();
        for(int i = 0; i < 4; i++) {
            if(AbstractLabyrinth.players[i].isAlive()) {
                p.add(AbstractLabyrinth.players[i]);
            }
        }
        count = p.size;
        items = new CharItemButton[count][2];
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        float ww = w / (count + 1);
        for(int i = 0; i < count; i++) {
            for(int j = 0; j < 2; j++) {
                CharItemButton t = new CharItemButton(this, j, p.get(i), this.item);
                t.setPosition(ww + ww * i + w * (0.08f * j - 0.04f) - t.sWidth * 0.5f, h * 0.775f - t.sHeight * 0.5f);
                items[i][j] = t;
            }
        }
        shopItem = new ShopItemIcon(this);
        shopItem.setPosition(w * 0.5f - shopItem.sWidth * 0.5f, h * 0.575f - shopItem.sHeight * 0.5f);
    }

    @Override
    public void update() {
        shopItem.update();
        if(type == TakeType.SKILL) {
            for (int i = 0; i < 4; i++) {
                deck[i].update();
            }
        } else {
            for(int i = 0; i < count; i++) {
                for(int j = 0; j < 2; j++) {
                    items[i][j].update();
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);

        shopItem.render(sb);
        if(type == TakeType.SKILL) {
            for (int i = 0; i < 4; i++) {
                deck[i].render(sb);
            }
        } else {
            for(int i = 0; i < count; i++) {
                for(int j = 0; j < 2; j++) {
                    items[i][j].render(sb);
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public enum TakeType {
        SKILL, ITEM
    }
}
