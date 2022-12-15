package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.rooms.other.ShopRoom;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class ShopScreen extends AbstractScreen {

    public ShopItemButton[] skills = new ShopItemButton[6];
    public ShopItemButton[] items = new ShopItemButton[5];
    public ShopItemButton roll;
    public PassShopButton pass;
    public ShopRoom room;

    public ShopScreen(ShopRoom room) {
        this.room = room;
        cType = ControlPanel.ControlType.BASIC;
        int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for (int i = 0; i < 6; i++) {
            ShopRoom.SkillItem s = room.skills[i];
            ShopItemButton b = new ShopItemButton(s);
            b.setPosition((662 + 200 * i) * scale, 957 * scale);
            AbstractUI.TempUI ui = new AbstractUI.TempUI(FileHandler.getUi().get("GOLD"),
                    (690 + 200 * i) * scale, (670 + 241) * scale, 35, 35);
            b.gold = ui;
            skills[i] = b;
        }
        for (int i = 0; i < 5; i++) {
            ShopItemButton b = new ShopItemButton(room.items[i]);
            b.setPosition((662 + 200 * i) * scale, 716 * scale);
            AbstractUI.TempUI ui = new AbstractUI.TempUI(FileHandler.getUi().get("GOLD"),
                    (690 + 200 * i) * scale, 670 * scale, 35, 35);
            b.gold = ui;
            items[i] = b;
        }
        roll = new ShopItemButton(room.roll);
        roll.setPosition(1662 * scale, 716 * scale);
        AbstractUI.TempUI ui = new AbstractUI.TempUI(FileHandler.getUi().get("GOLD"),
                1690 * scale, 670 * scale, 35, 35);
        roll.gold = ui;
        pass = new PassShopButton();
        setBg(FileHandler.getBg().get("BG_SHOP"));
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
        }
        for (ShopItemButton b : items) b.render(sb);
        roll.render(sb);
        String t;
        if(AbstractLabyrinth.advisor.id.equals("rusuk")) t = "∞";
        else if(!roll.item.isDone) t = "1";
        else t = "&r<0>";
        FontHandler.renderColorCenter(sb, FontHandler.ROLL, t, 1864 * scale, 819 * scale, 56 * scale);
        pass.render(sb);
    }

    @Override
    public void show() {
        SoundHandler.addWay().stop = false;
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
