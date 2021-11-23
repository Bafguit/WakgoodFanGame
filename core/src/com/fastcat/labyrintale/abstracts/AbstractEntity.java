package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.BATTLE;

public abstract class AbstractEntity implements Cloneable {


    protected Texture orb;
    protected Texture sOrb;

    public TextureAtlas atlas;
    public Skeleton skeleton;
    public AnimationState state;
    public AnimationStateData stateData;

    public Array<AbstractSkill> deck;
    public AbstractSkill[] hand = new AbstractSkill[4];
    public Array<AbstractSkill> drawPile;
    public Array<AbstractSkill> discardPile;
    public Array<AbstractStatus> status;
    public EntityType entityType;
    public String id;
    public String name;
    public String description;
    public boolean targetable;
    public boolean isDead;
    public int health;
    public int maxHealth;

    public AbstractEntity(String id, EntityType type, int maxHealth) {
        this.id = id;
        /** 여기에 json 받아오는거 입력 */
        /** 여기에 아틀라스 이미지 불러오는거 입력 */
        this.entityType = type;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.drawPile = new Array<>();
        this.discardPile = new Array<>();
        this.status = new Array<>();
    }

    public void update() {
        if(currentFloor.currentRoom.type == BATTLE) {

        }
    }

    public void render(SpriteBatch sb) {

    }
    
    public void die() {
        this.isDead = true;
    }

    public abstract Array<AbstractSkill> getStartingDeck();
    
    public enum EntityType {
        PLAYER, ENEMY
    }
}
