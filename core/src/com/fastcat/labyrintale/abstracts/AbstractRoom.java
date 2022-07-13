package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.rooms.other.RestRoom;
import com.fastcat.labyrintale.rooms.other.ShopRoom;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;
import com.fastcat.labyrintale.screens.shop.ShopScreen;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.Labyrintale.eventScreen;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public class AbstractRoom {

    public String id;
    public String text;
    public AbstractEnemy[] enemies;
    public AbstractEvent event;
    public RoomType type;
    public boolean battleDone;
    public boolean isDone;

    public AbstractRoom() {

    }

    public AbstractRoom(AbstractEvent event) {
        this(event.id, EVENT);
        this.event = event;
    }

    public AbstractRoom(String id, RoomType type) {
        this.id = id;
        enemies = getEnemies();
        for(int i = 0; i < enemies.length; i++) {
            enemies[i].defineIndex(i);
        }
        this.type = type;
        this.isDone = false;
        this.battleDone = false;
    }
    
    public void update() {
    }
    
    public void render(SpriteBatch sb) {
        
    }

    public final void enter() {
        if(type == MYSTERY) {
            int b = 10, s = 10, r = 10, e = 70;
            if(AbstractLabyrinth.bleak >= 20) {
                b = 35;
                if(AbstractLabyrinth.bleak >= 40) {
                    r = 0;
                }
            }
            s = s + b;
            r = r + s;
            e = e + r;
            int x = AbstractLabyrinth.mapRandom.random(e);
            AbstractRoom temp;
            if(x < b) {
                //TODO 랜덤으로 변경
                temp = GroupHandler.RoomGroup.normalGroup.get(AbstractLabyrinth.currentFloor.floorNum).get(0).cpy();
                enemies = temp.enemies;
            } else if (x < s) {
                temp = new ShopRoom();
            } else if (x < r) {
                temp = new RestRoom();
            } else {
                temp = GroupHandler.RoomGroup.getNextEvent();
                event = temp.event;
            }
            id = temp.id;
            type = temp.type;
            done();
            battleDone = true;
            AbstractLabyrinth.currentFloor.currentRoom = temp;
        }
        if (type == AbstractRoom.RoomType.BATTLE || type == AbstractRoom.RoomType.ELITE || type == AbstractRoom.RoomType.BOSS) {
            SoundHandler.fadeOutMusic("MAP");
            SoundHandler.addMusic("BATTLE_1", true, true);
            battleScreen = new BattleScreen();
            fadeOutAndChangeScreen(battleScreen);
        } else if(type == AbstractRoom.RoomType.REST) {
            restScreen = new RestScreen();
            fadeOutAndChangeScreen(restScreen);
        } else if(type == AbstractRoom.RoomType.EVENT) {
            eventScreen = new EventScreen(event);
            fadeOutAndChangeScreen(eventScreen);
        } else if(type == SHOP) {
            ShopRoom r = new ShopRoom();
            r.entry();
            shopScreen = new ShopScreen(r);
            fadeOutAndChangeScreen(shopScreen);
        }
    }

    public AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
    
    public void done() {
        this.isDone = true;
    }

    public final AbstractRoom cpy() {
        AbstractRoom r = new AbstractRoom();
        r.id = id;
        r.text = text;
        r.enemies = getEnemies();
        if(event != null) r.event = event.clone();
        r.type = type;
        r.battleDone = battleDone;
        r.isDone = isDone;
        return r;
    }

    public enum RoomType {
        ENTRY, BATTLE, ELITE, BOSS, EVENT, SHOP, REST, MYSTERY
    }
}
