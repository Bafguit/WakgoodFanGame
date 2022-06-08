package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.enemies.EnemyPlaceholder;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;
import com.fastcat.labyrintale.screens.shop.ShopScreen;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.Labyrintale.eventScreen;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public class AbstractRoom implements Cloneable {

    public String id;
    public String text;
    public AbstractEnemy[] enemies;
    public AbstractEvent event;
    public RoomType type;
    public boolean battleDone;
    public boolean isDone;
    
    public AbstractRoom() {
        this("Entry", ENTRY);
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
        entry();
        if (type == AbstractRoom.RoomType.BATTLE || type == AbstractRoom.RoomType.ELITE || type == AbstractRoom.RoomType.BOSS) {
            battleScreen = new BattleScreen();
            fadeOutAndChangeScreen(battleScreen);
        } else if(type == AbstractRoom.RoomType.REST) {
            restScreen = new RestScreen();
            fadeOutAndChangeScreen(restScreen);
        } else if(type == AbstractRoom.RoomType.EVENT) {
            eventScreen = new EventScreen(event);
            fadeOutAndChangeScreen(eventScreen);
        } else if(type == SHOP) {
            shopScreen = new ShopScreen();
            fadeOutAndChangeScreen(shopScreen);
        }
    }

    public void entry() {

    }

    protected AbstractEnemy[] getEnemies() {
        return new AbstractEnemy[]{new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder(), new EnemyPlaceholder()};
    }
    
    public void done() {
        this.isDone = true;
    }

    @Override
    public final AbstractRoom clone() {
        try {
            return (AbstractRoom) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum RoomType {
        ENTRY, BATTLE, ELITE, BOSS, EVENT, SHOP, REST
    }
}
