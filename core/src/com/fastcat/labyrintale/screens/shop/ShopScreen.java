package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.rooms.other.Shop;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

public class ShopScreen extends AbstractScreen {

    public ShopItemButton[] skills = new ShopItemButton[6];
    public ShopItemButton remove;

    public ShopScreen() {
        AbstractRoom room = currentFloor.currentRoom;
        if(room instanceof Shop) {
            for(int i = 0; i < 6; i++) {
                skills[i] = new ShopItemButton(((Shop) room).skills[i]);
            }
            remove = new ShopItemButton(((Shop) room).remove);
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
