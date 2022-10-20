package com.fastcat.labyrintale.screens.itemselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.interfaces.GetSelectedItem;
import com.fastcat.labyrintale.uis.BgImg;

public class ItemSelectScreen extends AbstractScreen implements GetSelectedItem {

  public BgImg bg = new BgImg();
  public ItemSelectText itemSelectText;
  public ItemButton selected;
  public ItemButton[] items;
  public GetSelectedItem gets;

  public ItemSelectScreen(AbstractItem[] items, GetSelectedItem gets) {
    itemSelectText = new ItemSelectText();
    this.gets = gets;
    int size = items.length;
    float w = Gdx.graphics.getWidth() * (1.0f / (size + 1)), h = Gdx.graphics.getHeight();
    for (int i = 0; i < size; i++) {
      ItemButton adv = new ItemButton(items[i], this);
      adv.setPosition(w * (i + 1) - adv.sWidth / 2, h * 0.6f);
      this.items[i] = adv;
    }
  }

  @Override
  public void update() {
    for (ItemButton item : items) {
      item.update();
    }
    itemSelectText.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    bg.render(sb);
    for (ItemButton advisorButton : items) {
      advisorButton.render(sb);
    }
    itemSelectText.render(sb);
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
    gets.itemSelected(item);
  }
}
