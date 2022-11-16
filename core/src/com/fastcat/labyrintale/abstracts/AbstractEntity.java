package com.fastcat.labyrintale.abstracts;

import static com.badlogic.gdx.graphics.Color.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

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
import com.fastcat.labyrintale.actions.*;
import com.fastcat.labyrintale.effects.DieEffect;
import com.fastcat.labyrintale.effects.HealthBarDamageEffect;
import com.fastcat.labyrintale.effects.UpDamageEffect;
import com.fastcat.labyrintale.effects.UpTextEffect;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.resultscreen.ResultScreen;
import com.fastcat.labyrintale.status.NeutResStatus;
import com.fastcat.labyrintale.status.NeutStatus;
import com.fastcat.labyrintale.uis.PlayerIcon;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

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
  public AbstractSkill move;
  public AbstractSkill moveTemp;
  public AbstractSkill pass;
  public AbstractSkill pre;
  public LinkedList<AbstractStatus> status = new LinkedList<>();
  public AbstractItem[] item = new AbstractItem[2];
  public AbstractItem passive;
  public String id;
  public String name;
  public String desc;
  public boolean isDead = false;
  public boolean isDie = false;
  public boolean isNeut = false;
  public int goodLuck = 0;
  public int badLuck = 0;
  public int movable = 0;
  public int index;
  public int block = 0;
  public int blockRemove = 0;
  public int health;
  public int maxHealth;
  public int minRes = 5;
  public int maxRes = 80;
  public float animX = -10000;
  public float animY = -10000;
  public EntityStat stat;
  public Sprite img;
  public Sprite imgTurn;
  public Sprite imgTiny;
  public Sprite imgPanel;
  public Sprite bg;

  public AbstractEntity(
      String id, int hand, int maxHealth, TextureAtlas atlas, FileHandle skel, boolean player) {
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
    stat = new EntityStat();

  }

  public void update() {
    if (hand != null) {
      for (AbstractSkill s : hand) {
        if(s != null) s.update();
      }
    }
  }

  public void atEndOfTurn() {

  }

  public void shuffleHand() {}

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
          // SoundHandler.playSfx("HEAL");
          if (cPanel.type == ControlPanel.ControlType.BATTLE) {
            EffectHandler.add(
                new UpDamageEffect(
                    ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, heal, CHARTREUSE, false));
          } else {
            PlayerIcon ui = cPanel.infoPanel.pIcons[index];
            cPanel.effectHandler.effectList.addLast(
                new UpDamageEffect(
                    ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.5f, heal, CHARTREUSE, false));
          }
          health = Math.min(health + heal, maxHealth);
          if (isPlayer) {
            passive.onHeal(heal);
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
    if (!hasStatus("Unblockable")) {
      if (b > 0) {
        if (isPlayer) {
          b = passive.onGainBlock(b);
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
          EffectHandler.add(
              new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, b, CYAN, false));
          block += b;
        }
      }
    }
  }

  public void applyStatus(AbstractStatus status, int amount, boolean effect) {
    applyStatus(status, null, amount, effect);
  }

  public void applyStatus(AbstractStatus status, AbstractEntity actor, int amount, boolean effect) {
    if(isAlive()) {
      boolean done = false;
      AbstractStatus s = getStatus("Immune");
      if (status.type == AbstractStatus.StatusType.DEBUFF) {
        if (s != null) {
          amount = -1;
          effect = true;
        } else if (actor != this) {
          int a = publicRandom.random(0, 99);
          if (badLuck > 1) a = Math.min(a, publicRandom.random(0, 99));
          if (goodLuck > 1) a = Math.max(a, publicRandom.random(0, 99));
          if (a < EntityStat.cap(stat.debuRes)) {
            EffectHandler.add(
                    new UpTextEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, "디버프 저항", CYAN));
            done = true;
          } else {
            s = Objects.requireNonNull(status.cpy());
            s.owner = this;
          }
        } else {
          s = Objects.requireNonNull(status.cpy());
          s.owner = this;
        }
      } else {
        s = Objects.requireNonNull(status.cpy());
        s.owner = this;
      }
      if (!done) {
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
              temp.onApply(amount);
            }
            if (effect) {
              temp.flash(this);
              EffectHandler.add(
                      new UpTextEffect(
                              animX,
                              animY + Gdx.graphics.getHeight() * 0.2f,
                              text,
                              s.id.equals("NeutE") ? SCARLET : YELLOW));
            }
            done = true;
            break;
          }
        }
        if (!done) {
          text = s.name + (s.hasAmount && amount != 0 ? (amount > 0 ? "+" + amount : amount) : "");
          if (actor == this) s.isSelf = true;
          this.status.addLast(s);
          s.onInitial();
          if (s.hasAmount) s.onApply(amount);
          if (effect) {
            s.flash(this);
            EffectHandler.add(
                    new UpTextEffect(
                            animX,
                            animY + Gdx.graphics.getHeight() * 0.2f,
                            text,
                            s.id.equals("NeutE") ? SCARLET : YELLOW));
          }
        }
      }
    }
  }

  public void applyStatus(AbstractStatus status, AbstractEntity actor, int amount) {
    applyStatus(status, actor, amount, true);
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
    d += stat.attack;
    if (isPlayer) {
      d = passive.showAttack(d);
      for (AbstractItem m : item) {
        if (m != null) d = m.showAttack(d);
      }
    }
    for (AbstractStatus s : status) {
      d = s.showAttack(d);
    }
    if (isPlayer) {
      d *= passive.attackMultiply(d);
      for (AbstractItem m : item) {
        if (m != null) d *= m.attackMultiply(d);
      }
    }
    for (AbstractStatus s : status) {
      d *= s.attackMultiply();
    }
    return critical(d);
  }

  private int critical(int d) {
    int cr = EntityStat.cap(stat.critical);
    if(hasItem("TotoDeck")) cr = 10;
    int a = publicRandom.random(0, 99);
    if(badLuck > 1) a = Math.min(a, publicRandom.random(0, 99));
    if(goodLuck > 1) a = Math.max(a, publicRandom.random(0, 99));
    if (a < cr) {
      EffectHandler.add(
              new UpTextEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, "치명타", CYAN));
      d *= 1 + ((float) stat.multiply * 0.01f);
    }
    return d;
  }

  public int calculateSpell(int d) {
    d += stat.spell;
    if (isPlayer) {
      d = passive.showSpell(d);
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

  public int takeDamage(DamageInfo info) {
    int damage = info.damage;
    if (isAlive()) {
      AbstractEntity attacker = info.actor;
      DamageType type = info.type;
      if (cPanel.type == ControlPanel.ControlType.BATTLE) {
        if (attacker != null && type == DamageType.NORMAL) {
          damage = attacker.calculateAttack(damage);
          if (attacker.isPlayer) {
            attacker.passive.onAttack(this, damage, type);
            for (AbstractItem m : attacker.item) {
              if (m != null) m.onAttack(this, damage, type);
            }
          }
          for (AbstractStatus s : attacker.status) {
            if (s != null) s.onAttack(this, damage, type);
          }
        } else if (attacker != null && type == DamageType.COUNTER) {
          damage = attacker.critical(damage);
        }
        if (damage > 0) {
          if (isPlayer) {
            damage = passive.onAttacked(attacker, damage, type);
            for (AbstractItem m : item) {
              if (m != null) damage = m.onAttacked(attacker, damage, type);
            }
          }
          for (AbstractStatus s : status) {
            if (s != null) damage = s.onAttacked(attacker, damage, type);
          }
          if (isPlayer) {
            damage *= passive.onAttackedMultiply(attacker, damage, type);
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
              if (attacker != null && type == DamageType.NORMAL) {
                if (attacker.isPlayer) {
                  attacker.passive.onDamage(this, damage, type);
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
                damage = passive.onDamaged(attacker, damage, type);
                for (AbstractItem m : item) {
                  if (m != null) damage = m.onDamaged(attacker, damage, type);
                }
              }
              for (AbstractSkill s : hand) {
                if (s != null) damage = s.onDamaged(attacker, damage, type);
              }
              for (AbstractStatus s : status) {
                if (s != null) damage = s.onDamaged(attacker, damage, type);
              }
              if (damage > 0) {
                EffectHandler.add(
                    new UpDamageEffect(
                        ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, damage, YELLOW, true));
                EffectHandler.add(new HealthBarDamageEffect(this));
                AnimationState.TrackEntry e = state.setAnimation(0, "hit", false);
                state.addAnimation(0, "idle", true, 0.0F);
                e.setTimeScale(1.0f);
                health -= damage;
                if (health <= 0) {
                  if (!isNeut && (isPlayer || !advisor.id.equals("callycarly"))) {
                    neutralize();
                    block = 0;
                    blockRemove = 0;
                  } else {
                    int a = publicRandom.random(0, 99);
                    if(badLuck > 0) a = Math.min(a, publicRandom.random(0, 99));
                    if(goodLuck > 0) a = Math.max(a, publicRandom.random(0, 99));
                    if(a < EntityStat.neutCap(this)) {
                      health = 1;
                      block = 0;
                      blockRemove = 0;
                      EffectHandler.add(
                              new UpTextEffect(
                                      ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, "죽음 저항", CYAN));
                      applyStatus(new NeutResStatus(5), 5, false);
                    } else {
                      die(attacker);
                    }
                  }
                }
                return damage;
              } else {
                return 0;
              }
            } else {
              return 0;
            }
          }
        }
        EffectHandler.add(
            new UpDamageEffect(ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, 0, YELLOW, true));
      } else if (isPlayer && damage > 0) {
        PlayerIcon ui = cPanel.infoPanel.pIcons[index];
        cPanel.effectHandler.effectList.addLast(
            new UpDamageEffect(
                ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.5f, damage, YELLOW, true));
        cPanel.effectHandler.effectList.addLast(new HealthBarDamageEffect(this));
        health -= damage;
        if (health <= 0) {
          health = 0;
          block = 0;
          die(attacker);
        }
      }
    }
    return damage;
  }

  public int loseBlock(int damage) {
    if (block > 0) {
      if (block >= damage) {
        if (isPlayer) {
          passive.onLoseBlock(damage);
          for (AbstractItem m : item) {
            if (m != null) m.onLoseBlock(damage);
          }
        }
        for (AbstractStatus s : status) {
          if (s != null) s.onLoseBlock(damage);
        }
        block -= damage;
        if (blockRemove > 0) blockRemove = Math.max(blockRemove - damage, 0);
        EffectHandler.add(
            new UpDamageEffect(
                ui.x + ui.sWidth / 2, ui.y + ui.sHeight * 0.35f, damage, CYAN, true));
        return 0;
      } else {
        if (isPlayer) {
          passive.onLoseBlock(block);
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
      ActionHandler.top(new MoveAction(this, this, 3));
      ActionHandler.top(new WaitAction(1));
      if (isPlayer) {
        passive.onDeath(murder);
        for (AbstractItem m : item) {
          if (m != null) m.onDeath(murder);
        }
      }
      for (AbstractStatus s : status) {
        if (s != null) {
          s.onDeath(murder);
          s.onRemove();
        }
      }
      status.clear();
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
            if (p.isAlive()) {
              p.pre = null;
              for (AbstractSkill s : p.hand) {
                if (s != null) s.atBattleEnd();
              }
              p.passive.atBattleEnd();
              for (AbstractItem m : p.item) {
                if (m != null) m.atBattleEnd();
              }
              for (AbstractStatus s : p.status) {
                if (s != null) {
                  s.atBattleEnd();
                  s.onRemove();
                }
              }
              p.status.clear();
            }
          }
          advisor.atBattleEnd();
          if (currentFloor.floorNum == 4 && currentFloor.num == 12) {
            ActionHandler.bot(new EndLabyrinthAction(DeadScreen.ScreenType.WIN));
          } else {
            ActionHandler.bot(new VictoryAction());
          }
        }
      } else {
        for (int i = 0; i < 4; i++) {
          a = players[i].isAlive();
          if (a) break;
        }
        if (!a) {
          ActionHandler.clear();
          ActionHandler.top(new EndLabyrinthAction(DeadScreen.ScreenType.DEAD));
        }
      }
    } else {
      isDead = true;
      if (!AbstractLabyrinth.stillAlive()) {
        ActionHandler.clear();
        SoundHandler.fadeOutAll();
        ActionHandler.clear();
        Labyrintale.fadeOutAndChangeScreen(new ResultScreen(DeadScreen.ScreenType.DEAD), 2.0f);
        SaveHandler.finish(false);
      } else {
        if(isPlayer && index < 3) {
          for(int i = index; i < 3; i++) {
            players[i] = players[i + 1];
            players[i].index = i;
          }
          index = 3;
          players[3] = (AbstractPlayer) this;
        }
      }
    }
  }

  public void neutralize() {
    health = 1;
    block = 0;
    applyStatus(new NeutStatus(this), this, 1);
  }

  public void gainSkill(int index, AbstractSkill skill) {
    deck.set(index, skill);
  }

  public void upgradeSkill(int index, int amount) {
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
    imgTurn = ib;
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

  public boolean hasSkill(String id) {
    if (status != null) {
      for (AbstractSkill s : deck) {
        if (s != null && s.id.equals(id)) return true;
      }
    }
    return false;
  }

  public boolean hasStatus(String id) {
    if (status != null) {
      for (AbstractStatus s : status) {
        if (s != null && s.id.equals(id)) return true;
      }
    }
    return false;
  }

  public boolean hasDebuff() {
    if(status != null && status.size() > 0) {
      for (AbstractStatus s : status) {
        if(s.type == AbstractStatus.StatusType.DEBUFF) return true;
      }
    }
    return false;
  }

  public boolean hasItem(String id) {
    if (status != null) {
      for (AbstractItem s : item) {
        if (s != null && s.id.equals(id)) return true;
      }
    }
    return false;
  }

  public final void beforeBattle() {
    preBattle();
  }

  public void preBattle() {}

  @Override
  public AbstractEntity clone() {
    try {
      return (AbstractEntity) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public enum DamageType {
    NORMAL,
    COUNTER,
    SPIKE,
    LOSE
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

  public static class EntityStat {
    public int attack = 0;
    public int spell = 0;
    public int speed = 0;
    public int critical = 5;
    public int multiply = 50;
    public int moveRes = 5;
    public int debuRes = 5;
    public int neutRes = 5;

    public static int cap(int i) {
      return Math.max(Math.min(i, 100), 5);
    }

    public static int neutCap(AbstractEntity e) {
      return Math.max(Math.min(e.stat.neutRes, e.maxRes), e.minRes);
    }

    public int capSpeed() {
      return Math.max(speed, -20);
    }
  }
}
