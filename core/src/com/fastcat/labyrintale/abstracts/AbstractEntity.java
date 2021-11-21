package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.BATTLE;

public abstract class AbstractEntity implements Cloneable {


    protected Texture orb;
    protected Texture sOrb;

    public Array<AbstractSkill> deck;
    public Array<AbstractSkill> hand;
    public Array<AbstractSkill> drawPile;
    public Array<AbstractSkill> discardPile;
    public Array<AbstractTalent> skill;
    public Array<AbstractStatus> status;
    public EntityType type;
    public String id;
    public String name;
    public String description;
    public boolean targetable;
    public boolean isDead;
    public int handSize;
    public int attack;
    public int baseAttack;
    public int staticAttack;
    public int spell;
    public int baseSpell;
    public int staticSpell;
    public int health;
    public int maxHealth;

    public AbstractEntity(String id, EntityType type, int handSize, int attack, int spell, int maxHealth) {
        this.id = id;
        /** 여기에 json 받아오는거 입력 */
        /** 여기에 아틀라스 이미지 불러오는거 입력 */
        this.type = type;
        this.handSize = handSize;
        this.attack = attack;
        this.baseAttack = this.attack;
        this.staticAttack = this.attack;
        this.spell = spell;
        this.baseSpell = this.spell;
        this.staticSpell = this.spell;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.deck = getStartingDeck();
        this.skill = getStartingSkill();
        this.hand = new Array<>();
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

    public abstract Array<AbstractTalent> getStartingSkill();
    
    public enum EntityType {
        PLAYER, ENEMY
    }
}
