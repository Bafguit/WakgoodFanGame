package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public abstract class AbstractRoom implements Cloneable {

    public AbstractEnemy[] enemies = new AbstractEnemy[4];
    public AbstractEvent event;
    public RoomType type;
    public boolean isDone;
    
    public AbstractRoom() {
        this(ENTRY);
    }
    
    public AbstractRoom(AbstractEvent event) {
        this(EVENT);
        this.event = event;
    }
    
    public AbstractRoom(AbstractEnemy[] enemies, int t) {
        this(t == 1 ? ELITE : t == 2 ? BOSS : BATTLE);
        for(int i = 0; i < enemies.length; i++) {
            enemies[i].defineIndex(i);
        }
        System.arraycopy(enemies, 0, this.enemies, 0, enemies.length);
    }

    public AbstractRoom(RoomType type) {
        this.type = type;
        this.isDone = false;
    }
    
    public void update() {
        if(type == BATTLE) {

        }
    }
    
    public void render(SpriteBatch sb) {
        
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
