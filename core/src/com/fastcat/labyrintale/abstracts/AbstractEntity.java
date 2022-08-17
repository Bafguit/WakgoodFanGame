package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.actions.DefeatAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.actions.VictoryAction;
import com.fastcat.labyrintale.effects.DieEffect;
import com.fastcat.labyrintale.effects.HealthBarDamageEffect;
import com.fastcat.labyrintale.effects.UpDamageEffect;
import com.fastcat.labyrintale.effects.UpTextEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.status.NeutStatus;
import com.fastcat.labyrintale.uis.PlayerIcon;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.util.Iterator;
import java.util.LinkedList;
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
    public LinkedList<AbstractStatus> status = new LinkedList<>();
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
        if (hand != null) {
            for (AbstractSkill s : hand) {
                s.update();
            }
        }
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
        maxHealth = Math.max(max, 1);
        health = heal ? maxHealth : Math.min(health, maxHealth);
    }

    public final void modifyMaxHealth(int add) {
        maxHealth = Math.max(maxHealth + add, 1);
        if (add >= 0) heal(add);
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
        if (cPanel != null) {
            if (isAlive() && !hasStatus("Neut")) {
                if (heal > 0) {
                    //SoundHandler.playSfx("HEAL");
                    if (cPanel.type == ControlPanel.ControlType.BATTLE) {
                        EffectHandler.add(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, heal, CHARTREUSE, false));
                    } else {
                        PlayerIcon ui = cPanel.infoPanel.pIcons[index];
                        cPanel.effectHandler.effectList.addLast(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.5f, heal, CHARTREUSE, false));
                    }
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
        } else {
            health = Math.min(health + heal, maxHealth);
        }
    }

    public void gainBlock(int b) {
        if(!hasStatus("Unblockable")) {
            if (b > 0) {
                if (isPlayer) {
                    for (AbstractItem m : item) {
                        if (m != null) b = m.onGainBlock(b);
                    }
                }
                if (status != null) {
                    for (AbstractStatus s : status) {
                        if (s != null) {
                            b = s.onGainBlock(b);
                        }
                    }
                }
                if (b > 0) {
                    EffectHandler.add(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, b, CYAN, false));
                    block += b;
                }
            }
        }
    }

    public void applyStatus(AbstractStatus status, int amount, boolean effect) {
        AbstractStatus s = getStatus("Immune");
        if (status.type == AbstractStatus.StatusType.DEBUFF && s != null) {
            amount = -1;
            effect = true;
        } else {
            s = Objects.requireNonNull(status.cpy());
            s.owner = this;
        }
        boolean done = false;
        String text;
        Iterator<AbstractStatus> it = this.status.iterator();
        while (it.hasNext()) {
            AbstractStatus temp = it.next();
            if (temp.id.equals(s.id)) {
                text = temp.name;
                if (temp.hasAmount && amount != 0) {
                    temp.amount += amount;
                    if ((temp.amount < 0 && !temp.canGoNegative) || temp.amount == 0) {
                        temp.onRemove();
                        it.remove();
                    }
                    text += amount > 0 ? "+" + amount : amount;
                }
                temp.onApply();
                if (effect) {
                    temp.flash(this);
                    EffectHandler.add(new UpTextEffect(animX, animY + Gdx.graphics.getHeight() * 0.2f, text, s.id.equals("NeutE") ? SCARLET : YELLOW));
                }
                done = true;
                break;
            }
        }
        if (!done) {
            text = s.name + (s.hasAmount && amount != 0 ? (amount > 0 ? "+" + amount : amount) : "");
            this.status.addLast(s);
            s.onInitial();
            s.onApply();
            if (effect) {
                s.flash(this);
                EffectHandler.add(new UpTextEffect(animX, animY + Gdx.graphics.getHeight() * 0.2f, text, s.id.equals("NeutE") ? SCARLET : YELLOW));
            }
        }
    }

    public void applyStatus(AbstractStatus status, int amount) {
        applyStatus(status, amount, true);
    }

    public void removeStatus(String id) {
        Iterator<AbstractStatus> it = status.iterator();
        while (it.hasNext()) {
            AbstractStatus e = it.next();
            if (e.id.equals(id)) {
                e.onRemove();
                it.remove();
                break;
            }
        }
    }

    public int calculateAttack(int d) {
        if (isPlayer) {
            for (AbstractItem m : item) {
                if (m != null) d = m.showAttack(d);
            }
        }
        for (AbstractStatus s : status) {
            d = s.showAttack(d);
        }
        if (isPlayer) {
            for (AbstractItem m : item) {
                if (m != null) d *= m.attackMultiply(d);
            }
        }
        for (AbstractStatus s : status) {
            d *= s.attackMultiply();
        }
        return d;
    }

    public int calculateSpell(int d) {
        if (isPlayer) {
            for (AbstractItem m : item) {
                if (m != null) d = m.showSpell(d);
            }
        }
        for (AbstractStatus s : status) {
            d = s.showSpell(d);
        }
        for (AbstractStatus s : status) {
            d *= s.spellMultiply();
        }
        return d;
    }

    public void takeDamage(DamageInfo info) {
        if (isAlive()) {
            AbstractEntity attacker = info.actor;
            int damage = info.damage;
            DamageType type = info.type;
            if (cPanel.type == ControlPanel.ControlType.BATTLE) {
                if (attacker != null && type == DamageType.NORMAL) {
                    damage = attacker.calculateAttack(damage);
                    if (attacker.isPlayer) {
                        for (AbstractItem m : attacker.item) {
                            if (m != null) m.onAttack(this, damage, type);
                        }
                    }
                    for (AbstractStatus s : attacker.status) {
                        if (s != null) s.onAttack(this, damage, type);
                    }
                    if (attacker.isPlayer) {
                        for (AbstractItem m : attacker.item) {
                            if (m != null) m.onAttack(this, damage, type);
                        }
                    }
                    for (AbstractStatus s : attacker.status) {
                        if (s != null) s.onAttack(this, damage, type);
                    }
                }
                if (damage > 0) {
                    if (isPlayer) {
                        for (AbstractItem m : item) {
                            if (m != null) damage = m.onAttacked(attacker, damage, type);
                        }
                    }
                    for (AbstractStatus s : status) {
                        if (s != null) damage = s.onAttacked(attacker, damage, type);
                    }
                    if (isPlayer) {
                        for (AbstractItem m : item) {
                            if (m != null) damage *= m.onAttackedMultiply(attacker, damage, type);
                        }
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
                                if (isPlayer && advisor.cls == AbstractAdvisor.AdvisorClass.SECRET && !advisor.skill.usedOnce) {
                                    advisor.skill.use();
                                    health = 1;
                                    block = 0;
                                } else if (!isNeut) {
                                    neutralize();
                                } else {
                                    die(attacker);
                                }
                            }
                            if (attacker != null && type == DamageType.NORMAL) {
                                if (isPlayer) {
                                    for (AbstractItem m : attacker.item) {
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
            } else if (isPlayer && damage > 0) {
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
        if (block > 0) {
            if (block >= damage) {
                if (isPlayer) {
                    for (AbstractItem m : item) {
                        if (m != null) m.onLoseBlock(damage);
                    }
                }
                for (AbstractStatus s : status) {
                    if (s != null) s.onLoseBlock(damage);
                }
                block -= damage;
                EffectHandler.add(new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, damage, CYAN, true));
                return 0;
            } else {
                if (isPlayer) {
                    for (AbstractItem m : item) {
                        if (m != null) m.onLoseBlock(block);
                    }
                }
                for (AbstractStatus s : status) {
                    if (s != null) s.onLoseBlock(block);
                }
                damage -= block;
                block = 0;
                return damage;
            }
        }

        return damage;
    }

    public void revive() {
        isDead = false;
        isDie = false;
        health = 1;
        animColor.a = 1.0f;
        resetAnimation();
        infoSpine.setAnimation("idle");
    }

    public void die(AbstractEntity murder) {
        health = 0;
        block = 0;
        if (cPanel.type == ControlPanel.ControlType.BATTLE) {
            isDie = true;
            ActionHandler.top(new MoveAction(this, 3));
            if (isPlayer) {
                for (AbstractItem m : item) {
                    if (m != null) m.onDeath(murder);
                }
            }
            for (AbstractStatus s : status) {
                if (s != null) s.onDeath(murder);
            }
            EffectHandler.add(new DieEffect(this));
            boolean a = false;
            if (!isPlayer) {
                for (int i = 0; i < 4; i++) {
                    a = currentFloor.currentRoom.enemies[i].isAlive();
                    if (a) break;
                }
                if (!a) {
                    ActionHandler.clear();
                    for (AbstractPlayer p : players) {
                        if(p.isAlive()) {
                            for (AbstractSkill s : p.hand) {
                                if (s != null) s.atBattleEnd();
                            }
                            for (AbstractItem m : p.item) {
                                if (m != null) m.atBattleEnd();
                            }
                            for (AbstractStatus s : p.status) {
                                if (s != null) s.atBattleEnd();
                            }
                        }
                    }
                    advisor.skill.atBattleEnd();
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
        health = 1;
        block = 0;
        applyStatus(new NeutStatus(this), 1);
    }

    public void gainSkill(int index, AbstractSkill skill) {
        for (int i = 0; i < slot[index]; i++) {
            skill.upgrade();
        }
        deck.set(index, skill);
    }

    public void upgradeSlot(int index, int amount) {
        slot[index] += amount;
        for (int i = 0; i < amount; i++) {
            deck.get(index).upgrade();
        }
    }

    public void defineIndex(int i) {
        index = i;
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

    public AbstractStatus getStatus(String id) {
        if (status != null) {
            for (AbstractStatus s : status) {
                if (s != null && s.id.equals(id)) return s;
            }
        }
        return null;
    }

    public boolean hasStatus(String id) {
        if (status != null) {
            for (AbstractStatus s : status) {
                if (s != null && s.id.equals(id)) return true;
            }
        }
        return false;
    }

    public void preBattle() {

    }

    @Override
    public AbstractEntity clone() {
        try {
            return (AbstractEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public enum DamageType {
        NORMAL, SPIKE, LOSE
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
            skeleton.setPosition(Gdx.graphics.getWidth() * 0.3f, Gdx.graphics.getHeight() * 0.475f);
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
}
