package com.fastcat.labyrintale.abstracts;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.rooms.other.RestRoom;
import com.fastcat.labyrintale.rooms.other.ShopRoom;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;
import com.fastcat.labyrintale.screens.shop.ShopScreen;

public abstract class AbstractRoom implements Cloneable {

    public String id;
    public String text;
    public AbstractEnemy[] enemies;
    public AbstractEvent event;
    public RoomType type;
    public boolean battleDone;
    public boolean isDone;

    public AbstractRoom() {}

    public AbstractRoom(AbstractEvent event) {
        this(event.id, EVENT);
        this.event = event;
    }

    public AbstractRoom(String id, RoomType type) {
        this.id = id;
        enemies = getEnemies();
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].defineIndex(i);
        }
        this.type = type;
        this.isDone = false;
        this.battleDone = false;
    }

    public void update() {}

    public void render(SpriteBatch sb) {}

    public final void refreshEnemy() {
        enemies = getEnemies();
    }

    public final void enter() {
        if (type == MYSTERY) {
            int b = 10, s = 5, r = 10, e = 75;
            s = s + b;
            r = r + s;
            e = e + r;
            int x = AbstractLabyrinth.mapRandom.random(e);
            if (x < b) {
                AbstractRoom temp;
                temp = AbstractLabyrinth.currentFloor.currentWay.enemies.clone();
                enemies = temp.getEnemies();
                id = temp.id;
                type = temp.type;
                SoundHandler.fadeOutAll();
                SoundHandler.addBattle();
                battleScreen = new BattleScreen();
                fadeOutAndChangeScreen(battleScreen);
            } else if (x < s) {
                ShopRoom temp;
                temp = new ShopRoom();
                id = temp.id;
                type = SHOP;
                temp.entry();
                shopScreen = new ShopScreen(temp);
                fadeOutAndChangeScreen(shopScreen);
            } else if (x < r) {
                AbstractRoom temp;
                temp = new RestRoom();
                id = temp.id;
                type = REST;
                restScreen = new RestScreen();
                fadeOutAndChangeScreen(restScreen);
            } else {
                event = GroupHandler.RoomGroup.getNextEvent(AbstractLabyrinth.floorNum);
                type = EVENT;
                eventScreen = new EventScreen(event);
                fadeOutAndChangeScreen(eventScreen);
            }
        } else if (type == AbstractRoom.RoomType.BATTLE || type == AbstractRoom.RoomType.ELITE) {
            refreshEnemy();
            SoundHandler.fadeOutAll();
            SoundHandler.addBattle();
            battleScreen = new BattleScreen();
            fadeOutAndChangeScreen(battleScreen);
        } else if (type == AbstractRoom.RoomType.BOSS) {
            refreshEnemy();
            SoundHandler.fadeOutAll();
            SoundHandler.addBattle();
            battleScreen = new BattleScreen();
            SoundHandler.playSfx("CHANGE_DOOR");
            fadeOutAndChangeScreen(battleScreen, FadeType.HORIZONTAL);
        } else if (type == AbstractRoom.RoomType.REST) {
            restScreen = new RestScreen();
            fadeOutAndChangeScreen(restScreen);
        } else if (type == AbstractRoom.RoomType.EVENT) {
            eventScreen = new EventScreen(event);
            fadeOutAndChangeScreen(eventScreen);
        } else if (type == SHOP) {
            ShopRoom r = new ShopRoom();
            r.entry();
            shopScreen = new ShopScreen(r);
            fadeOutAndChangeScreen(shopScreen);
        }
    }

    public abstract AbstractEnemy[] getEnemies();

    public void done() {
        this.isDone = true;
    }

    @Override
    public AbstractRoom clone() {
        try {
            return (AbstractRoom) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public enum RoomType {
        ENTRY,
        BATTLE,
        ELITE,
        BOSS,
        EVENT,
        SHOP,
        REST,
        MYSTERY
    }
}
