package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.uis.CardPanel;

import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public class AbstractRoom implements Cloneable {

    private CardPanel cardPanel;
    private CardPanel cardPanel2;

    public AbstractSkill[] cards = new AbstractSkill[2];
    public AbstractEnemy[] enemies = new AbstractEnemy[5];
    public AbstractEvent event;
    public RoomType type;
    public boolean isDone;
    
    public AbstractRoom() {
        this(NONE);
    }
    
    public AbstractRoom(AbstractEvent event) {
        this(EVENT);
        this.event = event;
    }
    
    public AbstractRoom(AbstractEnemy[] enemies) {
        this(BATTLE);
        this.enemies = enemies;
    }

    public AbstractRoom(RoomType type) {
        this.type = type;
        this.isDone = false;
    }
    
    public void update() {
        if(type == BATTLE) {
            cardPanel.enable();
            cardPanel2.enable();
        }
    }
    
    public void render(SpriteBatch sb) {
        
    }
    
    public void done() {
        this.isDone = true;
    }

    public enum RoomType {
        NONE, BATTLE, EVENT, SHOP, REST, REWARD
    }
}
