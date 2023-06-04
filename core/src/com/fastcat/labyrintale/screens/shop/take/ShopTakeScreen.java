package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedItem;
import com.fastcat.labyrintale.uis.BgImg;

public class ShopTakeScreen extends AbstractScreen implements GetSelectedItem, GetRewardDone {

    private final BgImg bg = new BgImg();
    public TakeType type;
    public AbstractSkill skill;
    public AbstractItem item;
    public ShopItemIcon shopItem;
    public CharDeckButton[] deck;
    public CharDeckButton[][] decks;
    public CharItemButton[][] items;
    public CancelShopButton cancel;
    public GetSelectedItem getItem;
    public GetRewardDone rewardDone;
    public boolean canCancel = true;
    public int count;

    public ShopTakeScreen(AbstractSkill skill) {
        this(skill, null);
    }

    public ShopTakeScreen(AbstractSkill skill, GetRewardDone rewardDone) {
        this.skill = skill;
        this.rewardDone = rewardDone;
        type = TakeType.SKILL;
        if(AbstractLabyrinth.mode == AbstractLabyrinth.Mode.FREE) {
            decks = new CharDeckButton[4][3];
            float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
            for(int k = 0; k < 4; k++) {
                for (int i = 0; i < 3; i++) {
                    CharDeckButton c = new CharDeckButton(this, AbstractLabyrinth.players[k], i, this.skill);
                    c.setScale(0.8f);
                    c.setPosition(w * (0.2f + 0.22f * k + 0.06f * (i - 1.5f)) - c.sWidth * 0.5f, h * 0.675f - c.sHeight * 0.5f);
                    decks[k][i] = c;
                }
            }
            shopItem = new ShopItemIcon(this);
            shopItem.setScale(0.8f);
            shopItem.setPosition(w * 0.5f - shopItem.sWidth * 0.5f, h * 0.475f - shopItem.sHeight * 0.5f);
        } else {
            deck = new CharDeckButton[3];
            float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
            for (int i = 0; i < 3; i++) {
                CharDeckButton c = new CharDeckButton(this, i, this.skill);
                c.setPosition(w * (0.4f + 0.1f * i) - c.sWidth * 0.5f, h * 0.675f - c.sHeight * 0.5f);
                deck[i] = c;
            }
            shopItem = new ShopItemIcon(this);
            shopItem.setPosition(w * 0.5f - shopItem.sWidth * 0.5f, h * 0.475f - shopItem.sHeight * 0.5f);
        }
        cancel = new CancelShopButton(this);
    }

    public ShopTakeScreen(AbstractItem item) {
        this(item, null);
    }

    public ShopTakeScreen(AbstractItem item, boolean cancel) {
        this(item, null);
        canCancel = cancel;
    }


    public ShopTakeScreen(AbstractItem item, GetSelectedItem gets) {
        this(item, null, gets);
    }

    public ShopTakeScreen(AbstractItem item, GetRewardDone rewardDone, GetSelectedItem gets) {
        this.item = item;
        type = TakeType.ITEM;
        this.rewardDone = rewardDone;
        getItem = gets;
        Array<AbstractPlayer> p = new Array<>();
        for (int i = 0; i < 4; i++) {
            if (AbstractLabyrinth.players[i].isAlive()) {
                p.add(AbstractLabyrinth.players[i]);
            }
        }
        count = p.size;
        items = new CharItemButton[count][2];
        float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
        float ww = w / (count + 1);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 2; j++) {
                CharItemButton t = new CharItemButton(this, j, p.get(i), this.item);
                t.setPosition(ww + ww * i + w * (0.08f * j - 0.04f) - t.sWidth * 0.5f, h * 0.675f - t.sHeight * 0.5f);
                items[i][j] = t;
            }
        }
        shopItem = new ShopItemIcon(this);
        shopItem.setPosition(w * 0.5f - shopItem.sWidth * 0.5f, h * 0.475f - shopItem.sHeight * 0.5f);
        cancel = new CancelShopButton(this);
    }

    @Override
    public void update() {
        shopItem.update();
        if (type == TakeType.SKILL) {
            for (int i = 0; i < 3; i++) {
                if(AbstractLabyrinth.mode == AbstractLabyrinth.Mode.FREE) {
                    for(int j = 0; j < 4; j++) {
                        decks[j][i].update();
                    }
                } else {
                    deck[i].update();
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < 2; j++) {
                    items[i][j].update();
                }
            }
        }
        if(canCancel) cancel.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);

        shopItem.render(sb);
        if (type == TakeType.SKILL) {
            for (int i = 0; i < 3; i++) {
                if(AbstractLabyrinth.mode == AbstractLabyrinth.Mode.FREE) {
                    for(int j = 0; j < 4; j++) {
                        decks[j][i].render(sb);
                    }
                } else {
                    deck[i].render(sb);
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < 2; j++) {
                    items[i][j].render(sb);
                }
            }
        }
        if(canCancel) cancel.render(sb);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    @Override
    public void itemSelected(AbstractItem item) {
        if (getItem != null) getItem.itemSelected(item);
    }

    @Override
    public void isRewardDone(boolean isDone) {
        if (rewardDone != null) rewardDone.isRewardDone(isDone);
    }

    public enum TakeType {
        SKILL,
        ITEM
    }
}
