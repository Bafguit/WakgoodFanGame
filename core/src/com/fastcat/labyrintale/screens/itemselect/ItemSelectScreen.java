package com.fastcat.labyrintale.screens.itemselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedItem;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;
import com.fastcat.labyrintale.uis.BgImg;

public class ItemSelectScreen extends AbstractScreen implements GetSelectedItem, GetRewardDone {

    public BgImg bg = new BgImg();
    public ItemSelectText itemSelectText;
    public ItemButton selected;
    public ItemButton[] items;
    public GetSelectedItem gets;
    public GetRewardDone rewardDone;
    public CancelItemButton cancel;
    public boolean pass;

    public ItemSelectScreen(int amount, GetSelectedItem gets, GetRewardDone rewardDone, boolean passable) {
        this(GroupHandler.ItemGroup.getRandomItem(amount).toArray(AbstractItem.class), gets, rewardDone, passable);
    }

    public ItemSelectScreen(AbstractItem[] items, GetSelectedItem gets, GetRewardDone rewardDone, boolean passable) {
        itemSelectText = new ItemSelectText();
        this.rewardDone = rewardDone;
        this.gets = gets;
        int size = items.length;
        float w = Gdx.graphics.getWidth() * (1.0f / (size + 1)), h = Gdx.graphics.getHeight();
        this.items = new ItemButton[size];
        for (int i = 0; i < size; i++) {
            ItemButton adv = new ItemButton(items[i], this);
            adv.setPosition(w * (i + 1) - adv.sWidth / 2, h * 0.55f);
            this.items[i] = adv;
        }
        pass = passable;
        cancel = new CancelItemButton(this);
    }

    @Override
    public void update() {
        for (ItemButton item : items) {
            item.update();
        }
        itemSelectText.update();
        if (pass) cancel.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (ItemButton advisorButton : items) {
            advisorButton.render(sb);
        }
        itemSelectText.render(sb);
        if (pass) cancel.render(sb);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        for (ItemButton advisorButton : items) {
            advisorButton.dispose();
        }
    }

    @Override
    public void itemSelected(AbstractItem item) {
        Labyrintale.addTempScreen(new ShopTakeScreen(item, this, gets));
    }

    @Override
    public void isRewardDone(boolean isDone) {
        if (rewardDone != null) rewardDone.isRewardDone(isDone);
        if (isDone) {
            Labyrintale.removeTempScreen(this);
        }
    }
}
