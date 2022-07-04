package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.actions.DefeatAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.actions.VictoryAction;
import com.fastcat.labyrintale.effects.HealthBarDamageEffect;
import com.fastcat.labyrintale.effects.UpDamageEffect;
import com.fastcat.labyrintale.effects.DieEffect;
import com.fastcat.labyrintale.effects.UpTextEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.status.NeutEStatus;
import com.fastcat.labyrintale.status.NeutPStatus;
import com.fastcat.labyrintale.uis.PlayerIcon;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.util.Objects;

import static com.badlogic.gdx.graphics.Color.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

public abstract class AbstractEntity implements Cloneable {

    public final int handSize;
    public final boolean isPlayer;

    public final transient Color animColor = new Color(1, 1, 1, 1);
    public transient HealthBarDamageEffect hbEffect = null;

    public TextureAtlas atlas;
    public Skeleton skeleton;
    public AnimationState state;
    public AnimationStateData stateData;
    public InfoSpine infoSpine;

    public AbstractUI ui;
    public Array<AbstractSkill> deck;
    public AbstractSkill[] hand;
    public AbstractSkill mLeft;
    public AbstractSkill mRight;
    public AbstractSkill mLeftTemp;
    public AbstractSkill mRightTemp;
    public AbstractStatus[] status = new AbstractStatus[4];
    public AbstractItem[] item = new AbstractItem[2];
    public int[] slot = new int[3];
    public String id;
    public String name;
    public String desc;
    public boolean isDead = false;
    public boolean isDie = false;
    public boolean isNeut = false;
    public int movable = 0;
    public int index;
    public int tempIndex;
    public int block = 0;
    public int health;
    public int maxHealth;
    public float animX = -10000;
    public float animY = -10000;
    public Sprite img;
    public Sprite imgBig;
    public Sprite imgTiny;
    public Sprite bg;

    public AbstractEntity(String id, int hand, int maxHealth, TextureAtlas atlas, FileHandle skel, boolean player) {
        this.id = id;
        isPlayer = player;
        handSize = hand;
        /** 여기에 json 받아오는거 입력 */
        /** 여기에 아틀라스 이미지 불러오는거 입력 */
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        deck = getStartingDeck();

        this.atlas = atlas;
        SkeletonJson json = new SkeletonJson(atlas);
        json.setScale(0.7f * InputHandler.scale);
        SkeletonData skeletonData = json.readSkeletonData(skel);
        skeleton = new Skeleton(skeletonData);
        skeleton.setColor(Color.WHITE);
        skeleton.setPosition(animX, animY);
        stateData = new AnimationStateData(skeletonData);
        state = new AnimationState(stateData);
        resetAnimation();
        infoSpine = new InfoSpine(atlas, skel);
    }

    public void update() {

    }

    public final void resetAnimation() {
        AnimationState.TrackEntry e = state.setAnimation(0, "idle", true);
        e.setTrackTime(MathUtils.random(0.0f, 1.0f));
        e.setTimeScale(1.0f);
    }

    public void render(SpriteBatch sb) {
        if (atlas != null && !isDead) {
            state.update(Labyrintale.tick);
            state.apply(skeleton);
            state.getCurrent(0).setTimeScale(1.0f);
            skeleton.updateWorldTransform();
            skeleton.setPosition(animX, animY);
            skeleton.setColor(animColor);
            sb.end();
            Labyrintale.psb.begin();
            Labyrintale.sr.draw(Labyrintale.psb, skeleton);
            Labyrintale.psb.end();
            sb.begin();
        }
    }

    public final void setMaxHealth(int max, boolean heal) {
        maxHealth = max;
        health = heal ? maxHealth : Math.min(health, maxHealth);
    }

    public final void modifyMaxHealth(int add) {
        maxHealth = Math.max(maxHealth + add, 1);
        if(add >= 0) heal(add);
        else health = Math.min(health, maxHealth);
    }

    public final void addMaxHealth(int add) {
        maxHealth = Math.max(maxHealth + add, 1);
        health = Math.min(health, maxHealth);
    }

    public void setAnimXY(float x, float y) {
        animX = x;
        animY = y;
    }

    public void heal(int heal) {
        if(isAlive()) {
            heal = calculateSpell(heal);
            if(heal > 0) {
                health = Math.min(health + heal, maxHealth);
                if (isPlayer) {
                    for (AbstractItem m : item) {
                        if (m != null) m.onHeal(heal);
                    }
                }
                for (AbstractStatus s : status) {
                    if (s != null) s.onHeal(heal);
                }
            }
        }
    }

    public void gainBlock(int b) {
        if(b > 0) {
            int temp = calculateSpell(b);
            if(isPlayer) {
                for (AbstractItem m : item) {
                    if (m != null) temp = m.onGainBlock(temp);
                }
            }
            if(status != null)  {
                for(AbstractStatus s : status) {
                    temp = s != null ? s.onGainBlock(temp) : temp;
                }
            }
            if(temp > 0) {
                EffectHandler.add(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, temp, CYAN, false));
                block += temp;
            }
        }
    }

    public void applyStatus(AbstractStatus status, int amount) {
        boolean done = false;
        AbstractStatus s = Objects.requireNonNull(status.cpy());
        s.owner = this;
        for (int i = 0; i < 4; i++) {
            if (this.status[i] != null) {
                AbstractStatus temp = this.status[i];
                if(temp.id.equals(s.id)) {
                    if (temp.hasAmount) {
                        temp.amount += amount;
                        if ((temp.amount < 0 && !temp.canGoNegative) || temp.amount == 0) {
                            temp.onRemove();
                            if (i < 3) {
                                this.status[i] = null;
                                System.arraycopy(this.status, i + 1, this.status, i, 3 - i);
                            }
                            this.status[3] = null;
                        }
                    }
                    temp.onApply();
                    temp.flash(this);
                    done = true;
                    break;
                }
            }
        }
        if(!done) {
            for (int i = 0; i < 4; i++) {
                if (this.status[i] == null) {
                    this.status[i] = s;
                    s.onApply();
                    s.flash(this);
                    done = true;
                    break;
                }
            }
        }
        if(!done) {
            this.status[3].onRemove();
            System.arraycopy(this.status, 0, this.status, 1, 3);
            this.status[0] = s;
            s.onApply();
            s.flash(this);
        }
    }

    public void removeStatus(String id) {
        for (int i = 0; i < 4; i++) {
            AbstractStatus s = this.status[i];
            if (s != null && s.id.equals(id)) {
                s.onRemove();
                this.status[i] = null;
                if (i < 3) System.arraycopy(this.status, i + 1, this.status, i, 3 - i);
                break;
            }
        }
    }

    public int calculateAttack(int d) {
        if(isPlayer) {
            for (AbstractItem m : item) {
                if (m != null) d = m.calculateAttack(d);
            }
        }
        for(AbstractStatus s : status) {
            if(s != null) d = s.calculateAttack(d);
        }
        return d;
    }

    public int calculateSpell(int d) {
        if(isPlayer) {
            for (AbstractItem m : item) {
                if (m != null) d = m.calculateSpell(d);
            }
        }
        for(AbstractStatus s : status) {
            if(s != null) d = s.calculateSpell(d);
        }
        return d;
    }

    public void takeDamage(DamageInfo info) {
        if(isAlive()) {
            AbstractEntity attacker = info.actor;
            int damage = info.damage;
            DamageType type = info.type;
            if(cPanel.type == ControlPanel.ControlType.BATTLE) {
                if (attacker != null) {
                    if (type == DamageType.NORMAL) damage = attacker.calculateAttack(damage);
                    if (isPlayer) {
                        for (AbstractItem m : item) {
                            if (m != null) damage = m.onAttack(this, damage, type);
                        }
                    }
                    for (AbstractSkill s : attacker.hand) {
                        if (s != null) damage = s.onAttack(this, damage, type);
                    }
                    for (AbstractStatus s : attacker.status) {
                        if (s != null) damage = s.onAttack(this, damage, type);
                    }
                    if (isPlayer) {
                        for (AbstractItem m : item) {
                            if (m != null) damage *= m.onAttackMultiply(this, damage, type);
                        }
                    }
                    for (AbstractSkill s : attacker.hand) {
                        if (s != null) damage *= s.onAttackMultiply(this, damage, type);
                    }
                    for (AbstractStatus s : attacker.status) {
                        if (s != null) damage *= s.onAttackMultiply(this, damage, type);
                    }
                }
                if (damage > 0) {
                    if (isPlayer) {
                        for (AbstractItem m : item) {
                            if (m != null) damage = m.onAttacked(attacker, damage, type);
                        }
                    }
                    for (AbstractSkill s : hand) {
                        if (s != null) damage = s.onAttacked(attacker, damage, type);
                    }
                    for (AbstractStatus s : status) {
                        if (s != null) damage = s.onAttacked(attacker, damage, type);
                    }
                    if (isPlayer) {
                        for (AbstractItem m : item) {
                            if (m != null) damage *= m.onAttackedMultiply(attacker, damage, type);
                        }
                    }
                    for (AbstractSkill s : hand) {
                        if (s != null) damage *= s.onAttackedMultiply(attacker, damage, type);
                    }
                    for (AbstractStatus s : status) {
                        if (s != null) damage *= s.onAttackedMultiply(attacker, damage, type);
                    }
                    if (damage > 0) {
                        damage = loseBlock(damage);
                        if (damage > 0) {
                            EffectHandler.add(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, damage, YELLOW, true));
                            EffectHandler.add(new HealthBarDamageEffect(this));
                            AnimationState.TrackEntry e = state.setAnimation(0, "hit", false);
                            state.addAnimation(0, "idle", true, 0.0F);
                            e.setTimeScale(1.0f);
                            health -= damage;
                            if (health <= 0) {
                                if (isPlayer && advisor.cls == AbstractAdvisor.AdvisorClass.SECRET && !advisor.skill.usedOnly) {
                                    advisor.skill.use();
                                    health = maxHealth;
                                    block = 0;
                                } else if (!isNeut) {
                                    health = 1;
                                    block = 0;
                                    neutralize();
                                } else {
                                    health = 0;
                                    block = 0;
                                    die(attacker);
                                }
                            }
                            if (attacker != null) {
                                if (isPlayer) {
                                    for (AbstractItem m : item) {
                                        if (m != null) m.onDamage(this, damage, type);
                                    }
                                }
                                for (AbstractSkill s : attacker.hand) {
                                    if (s != null) s.onDamage(this, damage, type);
                                }
                                for (AbstractStatus s : attacker.status) {
                                    if (s != null) s.onDamage(this, damage, type);
                                }
                            }
                            if (isPlayer) {
                                for (AbstractItem m : item) {
                                    if (m != null) m.onDamaged(attacker, damage, type);
                                }
                            }
                            for (AbstractSkill s : hand) {
                                if (s != null) s.onDamaged(attacker, damage, type);
                            }
                            for (AbstractStatus s : status) {
                                if (s != null) s.onDamaged(attacker, damage, type);
                            }
                            return;
                        }
                        return;
                    }
                }
                EffectHandler.add(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, 0, YELLOW, true));
            } else if(isPlayer && damage > 0) {
                PlayerIcon ui = cPanel.infoPanel.pIcons[index];
                cPanel.effectHandler.effectList.addLast(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.5f, damage, YELLOW, true));
                cPanel.effectHandler.effectList.addLast(new HealthBarDamageEffect(this));
                health -= damage;
                if (health <= 0) {
                    health = 0;
                    block = 0;
                    die(attacker);
                }
            }
        }
    }

    public int loseBlock(int damage) {
        if(block > 0) {
            if(block >= damage) {
                if(isPlayer) {
                    for (AbstractItem m : item) {
                        if (m != null) m.onLoseBlock(damage);
                    }
                }
                for(AbstractStatus s : status) {
                    if(s != null) s.onLoseBlock(damage);
                }
                block -= damage;
                EffectHandler.add(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, damage, CYAN, true));
                return 0;
            } else {
                if(isPlayer) {
                    for (AbstractItem m : item) {
                        if (m != null) m.onLoseBlock(block);
                    }
                }
                for(AbstractStatus s : status) {
                    if(s != null) s.onLoseBlock(block);
                }
                damage -= block;
                block = 0;
                return damage;
            }
        }

        return damage;
    }
    
    public void die(AbstractEntity murder) {
        if(cPanel.type == ControlPanel.ControlType.BATTLE) {
            isDie = true;
            if(isPlayer) {
                ActionHandler.top(new MoveAction((AbstractPlayer) this, 3));
                for (AbstractItem m : item) {
                    if (m != null) m.onDeath(murder);
                }
            } else {
                ActionHandler.top(new MoveAction((AbstractEnemy) this, 3));
            }
            for(AbstractStatus s : status) {
                if(s != null) s.onDeath(murder);
            }
            EffectHandler.add(new DieEffect(this));
            boolean a = false;
            if(!isPlayer) {
                for (int i = 0; i < 4; i++) {
                    a = currentFloor.currentRoom.enemies[i].isAlive();
                    if (a) break;
                }
                if (!a) {
                    ActionHandler.clear();
                    for (AbstractItem m : item) {
                        if (m != null) m.atBattleEnd();
                    }
                    for(AbstractStatus s : status) {
                        if(s != null) s.atBattleEnd();
                    }
                    ActionHandler.bot(new VictoryAction());
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    a = players[i].isAlive();
                    if (a) break;
                }
                if (!a) ActionHandler.top(new DefeatAction());
            }
        } else isDead = true;
    }

    public void neutralize() {
        EffectHandler.add(new UpTextEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, "무력화", SCARLET));
        applyStatus(isPlayer ? new NeutPStatus(this) : new NeutEStatus(this), 1);
    }

    public void gainSkill(int index, AbstractSkill skill) {
        for(int i = 0; i < slot[i]; i++) {
            skill.upgrade();
        }
        deck.set(index, skill);
    }

    public void defineIndex(int i) {
        index = i;
        tempIndex = i;
    }

    public boolean isAlive() {
        return !isDead && !isDie;
    }

    public abstract Array<AbstractSkill> getStartingDeck();

    public abstract void newDeck();

    public void setImage(Sprite i, Sprite ib, Sprite bg) {
        img = i;
        imgBig = ib;
        this.bg = bg;
    }

    @Override
    public AbstractEntity clone() {
        try {
            return (AbstractEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static class DamageInfo {

        public AbstractEntity actor;
        public int damage;
        public DamageType type;

        public DamageInfo(AbstractEntity actor, int damage, DamageType type) {
            this.actor = actor;
            this.damage = damage;
            this.type = type;
        }

        public DamageInfo(AbstractEntity actor, int damage) {
            this.actor = actor;
            this.damage = damage;
            this.type = DamageType.NORMAL;
        }
    }

    public static class InfoSpine {

        public final TextureAtlas atlas;
        public final Skeleton skeleton;
        public final AnimationState state;
        public final AnimationStateData stateData;

        public InfoSpine(TextureAtlas atlas, FileHandle skel) {
            this.atlas = atlas;
            SkeletonJson json = new SkeletonJson(atlas);
            json.setScale(InputHandler.scale);
            SkeletonData skeletonData = json.readSkeletonData(skel);
            skeleton = new Skeleton(skeletonData);
            skeleton.setColor(Color.WHITE);
            skeleton.setPosition(Gdx.graphics.getWidth() * 0.3f, Gdx.graphics.getHeight() * 0.525f);
            stateData = new AnimationStateData(skeletonData);
            state = new AnimationState(stateData);
            AnimationState.TrackEntry e = state.setAnimation(0, "idle", true);
            e.setTrackTime(MathUtils.random(0.0f, 1.0f));
            e.setTimeScale(1.0f);
        }

        public void render(SpriteBatch sb) {
            if (atlas != null) {
                state.update(Labyrintale.tick);
                state.apply(skeleton);
                state.getCurrent(0).setTimeScale(1.0f);
                skeleton.updateWorldTransform();
                skeleton.setColor(WHITE.cpy());
                sb.end();
                Labyrintale.psb.begin();
                Labyrintale.sr.draw(Labyrintale.psb, skeleton);
                Labyrintale.psb.end();
                sb.begin();
            }
        }

        public void setAnimation(String key) {
            AnimationState.TrackEntry e = state.setAnimation(0, key, true);
            e.setTrackTime(MathUtils.random(0.0f, 1.0f));
            e.setTimeScale(1.0f);
        }
    }
    
    public enum DamageType {
        NORMAL, SPIKE, LOSE
    }
}
