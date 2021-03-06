package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.rooms.other.ShopRoom;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class ShopScreen extends AbstractScreen {

    public ShopItemButton[] skills = new ShopItemButton[6];
    public ShopItemButton[] items = new ShopItemButton[5];
    public ShopItemCharIcon[] icons = new ShopItemCharIcon[6];
    public ShopItemButton roll;
    public PassShopButton pass;

    public ShopScreen(ShopRoom room) {
        cType = ControlPanel.ControlType.BASIC;
        int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for (int i = 0; i < 6; i++) {
            ShopRoom.SkillItem s = room.skills[i];
            ShopItemButton b = new ShopItemButton(s);
            b.setPosition(w * (0.25f + 0.1f * i) - b.sWidth / 2, h * 0.75f);
            skills[i] = b;

            ShopItemCharIcon c = new ShopItemCharIcon(s);
            c.setPosition(b.x + b.sWidth - c.sWidth, b.y);
            icons[i] = c;
        }
        for (int i = 0; i < 5; i++) {
            ShopItemButton b = new ShopItemButton(room.items[i]);
            b.setPosition(w * (0.25f + 0.1f * i) - b.sWidth / 2, h * 0.55f);
            items[i] = b;
        }
        roll = new ShopItemButton(room.roll);
        roll.setPosition(w * 0.75f - roll.sWidth / 2, h * 0.55f);
        pass = new PassShopButton();
    }

    @Override
    public void update() {
        for (ShopItemButton b : skills) b.update();
        for (ShopItemButton b : items) b.update();
        roll.update();
        pass.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for (int i = 0; i < 6; i++) {
            skills[i].render(sb);
            icons[i].render(sb);
        }
        for (ShopItemButton b : items) b.render(sb);
        roll.render(sb);
        pass.render(sb);
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
