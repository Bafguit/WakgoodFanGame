package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.rooms.other.ShopRoom;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

public class ShopScreen extends AbstractScreen {

    public ShopItemButton[] skills = new ShopItemButton[6];
    public ShopItemButton[] items = new ShopItemButton[5];
    public ShopItemButton roll;

    public ShopScreen() {
        AbstractRoom room = currentFloor.currentRoom;
        int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        if(room instanceof ShopRoom) {
            ShopRoom shop = (ShopRoom) room;
            for(int i = 0; i < 6; i++) {
                ShopItemButton b = new ShopItemButton(shop.skills[i]);
                b.setPosition(w * (0.25f + 0.1f * i) - b.sWidth / 2, h * 0.7f);
            }
            for(int i = 0; i < 5; i++) {
                ShopItemButton b = new ShopItemButton(shop.items[i]);
                b.setPosition(w * (0.25f + 0.1f * i) - b.sWidth / 2, h * 0.55f);
            }
            roll = new ShopItemButton(shop.roll);
            roll.setPosition(w * 0.75f - roll.sWidth / 2, h * 0.55f);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

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
}
