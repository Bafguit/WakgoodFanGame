package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.actions.VictoryAction;
import com.fastcat.labyrintale.effects.UpTextEffect;
import com.fastcat.labyrintale.effects.DieEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.badlogic.gdx.graphics.Color.*;
import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public abstract class AbstractEntity implements Cloneable {

    private final int handSize;

    protected Texture orb;
    protected Texture sOrb;

    public TextureAtlas atlas;
    public Skeleton skeleton;
    public AnimationState state;
    public AnimationStateData stateData;

    public AbstractUI ui;
    public Array<AbstractSkill> deck;
    public AbstractSkill[] hand;
    public Array<AbstractSkill> drawPile;
    public Array<AbstractSkill> discardPile;
    public Array<AbstractSkill> disposablePile;
    public AbstractStatus status;
    public EntityType entityType;
    public String id;
    public String name;
    public String desc;
    public boolean isDead = false;
    public boolean isDie = false;
    public int block = 0;
    public int health;
    public int maxHealth;
    public float animX = -10000;
    public float animY = -10000;

    public AbstractEntity(String id, EntityType type, int hand, int maxHealth, TextureAtlas atlas, FileHandle skel) {
        this.id = id;
        handSize = hand;
        /** 여기에 json 받아오는거 입력 */
        /** 여기에 아틀라스 이미지 불러오는거 입력 */
        entityType = type;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        deck = getStartingDeck();
        drawPile = new Array<>();
        discardPile = new Array<>();
        disposablePile = new Array<>();
        newDeck();

        this.atlas = atlas;
        SkeletonJson json = new SkeletonJson(atlas);
        json.setScale(0.65f * InputHandler.scale);
        SkeletonData skeletonData = json.readSkeletonData(skel);
        skeleton = new Skeleton(skeletonData);
        skeleton.setColor(Color.WHITE);
        skeleton.setPosition(animX, animY);
        stateData = new AnimationStateData(skeletonData);
        state = new AnimationState(stateData);
        AnimationState.TrackEntry e = state.setAnimation(0, "Standby", true);
        e.setTrackTime(MathUtils.random(0.0f, 1.0f));
        e.setTimeScale(1.0f);
    }

    public void update() {

    }

    public void render(SpriteBatch sb) {
        if (atlas != null && !isDead) {
            state.update(Gdx.graphics.getDeltaTime());
            state.apply(skeleton);
            state.getCurrent(0).setTimeScale(1.0f);
            skeleton.updateWorldTransform();
            skeleton.setPosition(animX, animY);
            skeleton.setColor(WHITE.cpy());
            sb.end();
            Labyrintale.psb.begin();
            Labyrintale.sr.draw(Labyrintale.psb, skeleton);
            Labyrintale.psb.end();
            sb.begin();
        }
    }

    public void newDeck() {
        hand = new AbstractSkill[handSize];
        drawPile.clear();
        discardPile.clear();
        disposablePile.clear();
        for(int i = 0; i < deck.size; i++) {
            drawPile.add(deck.get(i).cpy());
        }
    }

    public void shuffleHand() {
        for(int i = 0; i < handSize; i++) {
            if(hand[i] != null) {
                discardPile.add(hand[i]);
            }
        }
        hand = new AbstractSkill[handSize];
        if(drawPile.size < handSize && discardPile.size > 0) {
            drawPile.addAll(discardPile);
            discardPile.clear();
        }
        drawPile.shuffle();
        int ts = drawPile.size;
        for(int i = 0; i < handSize; i++) {
            if(i < ts) {
                hand[i] = drawPile.get(0);
                drawPile.removeIndex(0);
            } else break;
        }
    }

    public void setAnimXY(float x, float y) {
        animX = x;
        animY = y;
    }

    public void heal(int heal) {
        if(heal < 0) heal = 0;
        if(isAlive()) {
            health = Math.min(health + heal, maxHealth);
            if(status != null) status.onHeal(heal);
        }
    }

    public void gainBlock(int b) {
        if(b > 0) {
            int temp = status != null ? status.onGainBlock(b) : b;
            if(temp > 0) {
                EffectHandler.add(new UpTextEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, temp, CYAN, false));
                block += temp;
            }
        }
    }

    public void damage(AbstractEntity actor, int damage) {
        int temp = loseBlock(damage);
        if(temp > 0) {
            EffectHandler.add(new UpTextEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, temp, YELLOW, true));
            AnimationState.TrackEntry e = state.setAnimation(0, "AirHitHurt", false);
            state.addAnimation(0, "Standby", true, 0.0F);
            e.setTimeScale(1.0f);
            health -= temp;
            if (health <= 0) {
                health = 0;
                block = 0;
                die();
            }
            if (status != null) status.onDamage(actor, damage);
        }
    }

    public int loseBlock(int damage) {
        if(block > 0) {
            if(block >= damage) {
                if (status != null) status.onLoseBlock(damage);
                block -= damage;
                EffectHandler.add(new UpTextEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, damage, CYAN, true));
                return 0;
            } else {
                if (status != null) status.onLoseBlock(block);
                damage -= block;
                block = 0;
                return damage;
            }
        }

        return damage;
    }
    
    public void die() {
        if(isAlive()) {
            if(currentFloor.currentRoom.type == BATTLE || currentFloor.currentRoom.type == ELITE || currentFloor.currentRoom.type == BOSS) {
                isDie = true;
                EffectHandler.add(new DieEffect(this));
                boolean a = false;
                for (int i = 0; i < 4; i++) {
                    a = currentFloor.currentRoom.enemies[i].isAlive();
                    if (a) break;
                }
                if (!a) ActionHandler.top(new VictoryAction());
            }
        }
    }

    public boolean isAlive() {
        return !isDead && !isDie;
    }

    public abstract Array<AbstractSkill> getStartingDeck();
    
    public enum EntityType {
        PLAYER, ENEMY
    }
}
