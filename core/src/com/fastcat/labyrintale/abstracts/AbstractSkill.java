package com.fastcat.labyrintale.abstracts;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.actions.NextTurnAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.actions.SetAnimationAction;
import com.fastcat.labyrintale.actions.TurnEndAction;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;
import com.fastcat.labyrintale.screens.battle.EnemyBattleView;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;
import com.fastcat.labyrintale.strings.KeyString;
import com.fastcat.labyrintale.strings.SkillString;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public abstract class AbstractSkill implements Cloneable, GetSelectedTarget {

    public final SkillType type;
    public Sprite img;
    public SkillString.SkillData skillData;
    public AbstractEntity owner;
    public SkillTarget target;
    public SkillRarity rarity;
    public String id;
    public String name;
    public String desc;
    public Array<AbstractUI.SubText> key = new Array<>();
    public boolean upgraded = false;
    public boolean usedOnce = false;
    public boolean usedOnly = false;
    public boolean passive = false;
    public boolean disposable = false;
    public boolean nextTurn = false;
    public int upgradeCount = 0;
    public int attack = -1;
    public int baseAttack = -1;
    public int upAttack = -1;
    public int spell = -1;
    public int baseSpell = -1;
    public int upSpell = -1;
    public int value = -1;
    public int baseValue = -1;
    public int upValue = -1;
    public int value2 = -1;
    public int baseValue2 = -1;
    public int upValue2 = -1;
    public int cost = 1;
    public int baseCost = 1;
    public int upCost = 0;

    public AbstractSkill(AbstractEntity owner, String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        this.owner = owner;
        this.id = id;
        if (rarity != SkillRarity.ENEMY) this.img = FileHandler.getSkillImg().get(this.id);
        this.skillData = StringHandler.skillString.get(this.id);
        this.name = this.skillData.NAME;
        this.desc = this.skillData.DESC;
        if (skillData.KEY != null) {
            for (String k : skillData.KEY) {
                KeyString.KeyData kd = StringHandler.keyString.get(k);
                key.add(new AbstractUI.SubText(kd.NAME, kd.DESC));
            }
        }
        this.type = type;
        this.target = target;
        this.rarity = rarity;
    }

    public AbstractSkill(String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        this(null, id, type, rarity, target);
    }

    public static Array<AbstractEntity> getTargets(AbstractEntity owner, SkillTarget target) {
        if (owner != null) {
            if (target == SkillTarget.SELF) {
                Array<AbstractEntity> temp = new Array<>();
                temp.add(owner);
                return temp;
            } else if (target == SkillTarget.RIGHT) {
                Array<AbstractEntity> temp = new Array<>();
                if (owner.isPlayer) {
                    if (owner.index > 0) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index - 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                } else {
                    if (owner.index < 3) {
                        AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.index + 1];
                        if (te.isAlive()) temp.add(te);
                    }
                }
                return temp;
            } else if (target == SkillTarget.LEFT) {
                Array<AbstractEntity> temp = new Array<>();
                if (owner.isPlayer) {
                    if (owner.index < 3) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index + 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                } else {
                    if (owner.index > 0) {
                        AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.index - 1];
                        if (te.isAlive()) temp.add(te);
                    }
                }
                return temp;
            } else if (target == SkillTarget.BOTH) {
                Array<AbstractEntity> temp = new Array<>();
                if (owner.isPlayer) {
                    if (owner.index > 0) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index - 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                    if (owner.index < 3) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index + 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                } else {
                    if (owner.index < 3) {
                        AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.index + 1];
                        if (te.isAlive()) temp.add(te);
                    }
                    if (owner.index > 0) {
                        AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.index - 1];
                        if (te.isAlive()) temp.add(te);
                    }
                }
                return temp;
            } else if (target == SkillTarget.SELF_RIGHT) {
                Array<AbstractEntity> temp = new Array<>();
                temp.add(owner);
                if (owner.isPlayer) {
                    if (owner.index > 0) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index - 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                } else {
                    if (owner.index < 3) {
                        AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.index + 1];
                        if (te.isAlive()) temp.add(te);
                    }
                }
                return temp;
            } else if (target == SkillTarget.SELF_LEFT) {
                Array<AbstractEntity> temp = new Array<>();
                temp.add(owner);
                if (owner.isPlayer) {
                    if (owner.index < 3) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index + 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                } else {
                    if (owner.index > 0) {
                        AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.index - 1];
                        if (te.isAlive()) temp.add(te);
                    }
                }
                return temp;
            } else if (target == SkillTarget.SELF_BOTH) {
                Array<AbstractEntity> temp = new Array<>();
                temp.add(owner);
                if (owner.isPlayer) {
                    if (owner.index > 0) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index - 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                    if (owner.index < 3) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index + 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                } else {
                    if (owner.index < 3) {
                        AbstractPlayer tp = AbstractLabyrinth.players[owner.index + 1];
                        if (tp.isAlive()) temp.add(tp);
                    }
                    if (owner.index > 0) {
                        AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.index - 1];
                        if (te.isAlive()) temp.add(te);
                    }
                }
                return temp;
            } else if (target == SkillTarget.OTHER) {
                Array<AbstractEntity> temp = new Array<>();
                if (owner.isPlayer) {
                    for (AbstractPlayer p : players) {
                        if (p != owner && p.isAlive()) temp.add(p);
                    }
                } else {
                    for (AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                        if (e != owner && e.isAlive()) temp.add(e);
                    }
                }
                return temp;
            }
        }
        return getTargets(target);
    }

    // 스킬 대상(유동)
    public static Array<AbstractEntity> getTargets(AbstractSkill s) {
        return getTargets(s.owner, s.target);
    }

    // 상태 대상(유동)
    public static Array<AbstractEntity> getTargets(AbstractStatus s) {
        return getTargets(s.owner, s.target);
    }

    // 고정 대상
    public static Array<AbstractEntity> getTargets(SkillTarget target) {
        Array<AbstractEntity> temp = new Array<>();
        PlayerBattleView[] tp = battleScreen.players;
        EnemyBattleView[] te = battleScreen.enemies;
        switch (target) {
            case NONE:
                break;
            case PLAYER_FIRST:
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case ENEMY_FIRST:
                for (int i = 0; i < 4; i++) {
                    AbstractEntity e = te[i].entity;
                    if (e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case PLAYER_LAST:
                boolean has = false;
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive() && p.hasStatus("Provoke")) {
                        temp.add(p);
                        has = true;
                        break;
                    }
                }
                if (!has) {
                    for (int i = 3; i >= 0; i--) {
                        AbstractEntity p = tp[i].entity;
                        if (p.isAlive()) {
                            temp.add(p);
                            break;
                        }
                    }
                }
                break;
            case ENEMY_LAST:
                has = false;
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive() && p.hasStatus("Provoke")) {
                        temp.add(p);
                        has = true;
                        break;
                    }
                }
                if (!has) {
                    for (int i = 3; i >= 0; i--) {
                        AbstractEntity e = te[i].entity;
                        if (e.isAlive()) {
                            temp.add(e);
                            break;
                        }
                    }
                }
                break;
            case PLAYER_FIRST_TWO:
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive()) temp.add(p);
                    if (temp.size == 2) break;
                }
                break;
            case ENEMY_FIRST_TWO:
                for (int i = 0; i < 4; i++) {
                    AbstractEntity e = te[i].entity;
                    if (e.isAlive()) temp.add(e);
                    if (temp.size == 2) break;
                }
                break;
            case PLAYER_LAST_TWO:
                for (int i = 3; i >= 0; i--) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive()) temp.add(p);
                    if (temp.size == 2) break;
                }
                break;
            case ENEMY_LAST_TWO:
                for (int i = 3; i >= 0; i--) {
                    AbstractEntity e = te[i].entity;
                    if (e.isAlive()) temp.add(e);
                    if (temp.size == 2) break;
                }
                break;
            case PLAYER_ALL:
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive()) temp.add(p);
                }
                break;
            case ENEMY_ALL:
                for (int i = 0; i < 4; i++) {
                    AbstractEntity e = te[i].entity;
                    if (e.isAlive()) temp.add(e);
                }
                break;
            case ALL:
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    AbstractEntity e = te[i].entity;
                    if (p.isAlive()) temp.add(p);
                    if (e.isAlive()) temp.add(e);
                }
                break;
            case PLAYER_LOW_HP:
                float low = 2147483647;
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive() && p.health < low) low = p.health;
                }
                for (PlayerBattleView p : tp) {
                    if (p.entity.health == low) temp.add(p.entity);
                }
                if (temp.size == 1) {
                    AbstractEntity e = temp.get(0);
                    for (int i = 0; i < 4; i++) {
                        AbstractEntity p = tp[i].entity;
                        if (p == e) break;
                        else if (p.isAlive() && p.hasStatus("Provoke")) {
                            temp.clear();
                            temp.add(p);
                            break;
                        }
                    }
                }
                break;
            case ENEMY_LOW_HP:
                low = 2147483647;
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = te[i].entity;
                    if (p.isAlive() && p.health < low) low = p.health;
                }
                for (EnemyBattleView p : te) {
                    if (p.entity.health == low) temp.add(p.entity);
                }
                if (temp.size == 1) {
                    AbstractEntity e = temp.get(0);
                    for (int i = 0; i < 4; i++) {
                        AbstractEntity p = te[i].entity;
                        if (p == e) break;
                        else if (p.isAlive() && p.hasStatus("Provoke")) {
                            temp.clear();
                            temp.add(p);
                            break;
                        }
                    }
                }
                break;
            case PLAYER_HIGH_HP:
                low = 0;
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = tp[i].entity;
                    if (p.isAlive() && p.health > low) low = p.health;
                }
                for (PlayerBattleView p : tp) {
                    if (p.entity.health == low) temp.add(p.entity);
                }
                if (temp.size == 1) {
                    AbstractEntity e = temp.get(0);
                    for (int i = 0; i < 4; i++) {
                        AbstractEntity p = tp[i].entity;
                        if (p == e) break;
                        else if (p.isAlive() && p.hasStatus("Provoke")) {
                            temp.clear();
                            temp.add(p);
                            break;
                        }
                    }
                }
                break;
            case ENEMY_HIGH_HP:
                low = 0;
                for (int i = 0; i < 4; i++) {
                    AbstractEntity p = te[i].entity;
                    if (p.isAlive() && p.health > low) low = p.health;
                }
                for (EnemyBattleView p : te) {
                    if (p.entity.health == low) temp.add(p.entity);
                }
                if (temp.size == 1) {
                    AbstractEntity e = temp.get(0);
                    for (int i = 0; i < 4; i++) {
                        AbstractEntity p = te[i].entity;
                        if (p == e) break;
                        else if (p.isAlive() && p.hasStatus("Provoke")) {
                            temp.clear();
                            temp.add(p);
                            break;
                        }
                    }
                }
                break;
        }
        return temp;
    }

    public static boolean noMoreSkill() {
        for (AbstractPlayer p : players) {
            if (p.isAlive()) {
                for (AbstractSkill s : p.hand) {
                    if (s.canUse() && !s.passive) return false;
                }
            }
        }
        return true;
    }

    protected void setIntent(IntentType type) {
        img = FileHandler.getSkillImg().get(type.toString());
    }

    protected final void top(AbstractAction a) {
        ActionHandler.top(a);
    }

    protected final void top(AbstractAction a, AbstractAction pre) {
        a.preAction = pre;
        ActionHandler.top(a);
    }

    protected final void bot(AbstractAction a) {
        ActionHandler.bot(a);
    }

    protected final void bot(AbstractAction a, AbstractAction pre) {
        a.preAction = pre;
        ActionHandler.bot(a);
    }

    public void setBaseAttack(int i) {
        baseAttack = i;
        attack = baseAttack;
    }

    public void setBaseSpell(int i) {
        baseSpell = i;
        spell = baseSpell;
    }

    public void setBaseValue(int i) {
        baseValue = i;
        value = baseValue;
    }

    public void setBaseCost(int i) {
        baseCost = i;
        cost = baseCost;
    }

    public void setBaseAttack(int i, int up) {
        baseAttack = i;
        attack = baseAttack;
        upAttack = up;
    }

    public void setBaseSpell(int i, int up) {
        baseSpell = i;
        spell = baseSpell;
        upSpell = up;
    }

    public void setBaseValue(int i, int up) {
        baseValue = i;
        value = baseValue;
        upValue = up;
    }

    public void setBaseValue2(int i) {
        baseValue2 = i;
        value2 = baseValue2;
    }

    public void setBaseValue2(int i, int up) {
        baseValue2 = i;
        value2 = baseValue2;
        upValue2 = up;
    }

    public void update() {
        attack = calculateAttack(baseAttack);
        spell = calculateSpell(baseSpell);
        value = calculateValue(baseValue);
        value2 = calculateValue2(baseValue2);
    }

    public void render(SpriteBatch sb) {}

    public final void flash() {
        flash(owner);
    }

    public final void flash(AbstractEntity e) {
        EffectHandler.add(new UpIconEffect(e.animX, e.animY + 288 * InputHandler.scale, img));
    }

    public int calculateAttack(int a) {
        return a;
    }

    public float showMultiply() {
        return 1.0f;
    }

    public float attackMultiply(AbstractEntity target) {
        return 1.0f;
    }

    public int calculateSpell(int s) {
        return s;
    }

    public int calculateValue(int v) {
        return v;
    }

    public int calculateValue2(int v) {
        return v;
    }

    public String getKeyValue(String key) {
        switch (key) {
            case "A":
                int a = baseAttack;
                if (AbstractLabyrinth.cPanel != null) {
                    a = calculateAttack(a + owner.stat.attack);
                    if (owner != null) {
                        if (owner.isPlayer) {
                            a = owner.passive.showAttack(a);
                            for (AbstractItem m : owner.item) {
                                if (m != null) a = m.showAttack(a);
                            }
                        }
                        for (AbstractStatus s : owner.status) {
                            if (s != null) a = s.showAttack(a);
                        }
                        a *= showMultiply();
                        if (owner.isPlayer) {
                            a *= owner.passive.attackMultiply(a);
                            for (AbstractItem m : owner.item) {
                                if (m != null) a *= m.attackMultiply(a);
                            }
                        }
                        for (AbstractStatus s : owner.status) {
                            if (s != null) a *= s.attackMultiply();
                        }
                    }
                    if (AbstractLabyrinth.cPanel.type == ControlPanel.ControlType.BATTLE
                            && battleScreen.looking.size == 1) {
                        AbstractEntity t = battleScreen.looking.get(0);
                        for (AbstractStatus s : t.status) {
                            if (s != null) a = s.showAttacked(a);
                        }
                        for (AbstractStatus s : t.status) {
                            if (s != null) a *= s.attackedMultiply();
                        }
                        if (t.isPlayer && t.id.equals("gosegu") && owner != null && owner.hasDebuff()) a *= 0.7f;
                    }
                }
                a = Math.max(a, 0);
                return getHexColor(valueColor(a, baseAttack)) + a;
            case "S":
                int p = baseSpell;
                if (AbstractLabyrinth.cPanel != null) {
                    p = calculateSpell(p + owner.stat.spell);
                    if (owner != null) {
                        if (owner.isPlayer) {
                            p = owner.passive.showSpell(p);
                            for (AbstractItem m : owner.item) {
                                if (m != null) p = m.showSpell(p);
                            }
                        }
                        for (AbstractStatus s : owner.status) {
                            if (s != null) p = s.showSpell(p);
                        }
                        if (owner.isPlayer) {
                            p *= owner.passive.spellMultiply(p);
                            for (AbstractItem m : owner.item) {
                                if (m != null) p *= m.spellMultiply(p);
                            }
                        }
                        for (AbstractStatus s : owner.status) {
                            if (s != null) p *= s.spellMultiply();
                        }
                    }
                }
                p = Math.max(p, 0);
                return getHexColor(valueColor(p, baseSpell)) + p;
            case "V":
                int v = baseValue;
                if (AbstractLabyrinth.cPanel != null) {
                    v = calculateValue(v);
                }
                return getHexColor(Color.CYAN) + v;
            case "I":
                int v2 = baseValue2;
                if (AbstractLabyrinth.cPanel != null) {
                    v2 = calculateValue(v2);
                }
                return getHexColor(Color.CYAN) + v2;
            default:
                return "ERROR_UNIDENTIFIABLE";
        }
    }

    private Color valueColor(int v, int base) {
        if (v < base) {
            return Color.SCARLET;
        } else if (v > base) {
            return Color.CHARTREUSE;
        } else {
            return Color.CYAN;
        }
    }

    public final void useCard() {
        if (owner != null) {
            for (AbstractStatus s : owner.status) {
                if (s != null) s.onUseCard(this);
            }
            owner.pre = this;
        }
        if (target == SkillTarget.ENEMY || target == SkillTarget.PLAYER) {
            bot(new SelectTargetAction(this));
        } else {
            use();
            if (owner != null && (type == SkillType.DEFENCE || type == SkillType.SCHEME)) {
                top(new SetAnimationAction(owner, "skill"));
            }
            if (disposable) usedOnce = true;
            if (owner != null) {
                /*top(new ZoomBeginAction());
                bot(new ZoomEndAction());*/
                bot(new TurnEndAction(owner));
                if (owner.isPlayer) bot(new NextTurnAction());
            }

            if (owner != null && owner.isPlayer) AbstractLabyrinth.energy -= cost;
        }
    }

    public final boolean canUse() {
        return !usedOnce && !usedOnly && available() && AbstractLabyrinth.energy >= cost;
    }

    protected boolean available() {
        return true;
    }

    protected abstract void use();

    public AbstractSkill upgrade() {
        if (upgradable()) {
            upgradeCard();
            if (upAttack != -1) {
                baseAttack += upAttack;
                attack = baseAttack;
            }
            if (upSpell != -1) {
                baseSpell += upSpell;
                spell = baseSpell;
            }
            if (upValue != -1) {
                baseValue += upValue;
                value = baseValue;
            }
            if (upValue2 != -1) {
                baseValue2 += upValue2;
                value2 = baseValue2;
            }
            if (upCost != 0) {
                baseCost = Math.max(baseCost + upCost, 0);
            }
            upgraded = true;
            upgradeCount++;
            name = skillData.NAME + "+" + upgradeCount;
        }
        return this;
    }

    public boolean upgradable() {
        return true;
    }

    protected abstract void upgradeCard();

    public void atBattleStart() {}

    public void atBattleEnd() {}

    public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {}

    public int onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        return damage;
    }

    @Override
    public final AbstractSkill clone() {
        try {
            return (AbstractSkill) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public final void onTargetSelected(AbstractEntity target) {
        beforeOnTarget();
        onTarget(target);
        afterOnTarget();
    }

    public void onTarget(AbstractEntity target) {}

    public final void beforeOnTarget() {
        if (owner != null && owner.isPlayer) AbstractLabyrinth.energy -= cost;
    }

    public final void afterOnTarget() {
        if (owner != null && (type == SkillType.DEFENCE || type == SkillType.SCHEME)) {
            top(new SetAnimationAction(owner, "skill"));
        }
        if (disposable) usedOnce = true;
        if (owner != null && owner.isPlayer) {
            /*top(new ZoomBeginAction());
            bot(new ZoomEndAction());*/
            bot(new TurnEndAction(owner));
            bot(new NextTurnAction());
        }
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        if (target == SkillTarget.ENEMY) {
            for (int i = 0; i < 4; i++) {
                EnemyBattleView pv = Labyrintale.battleScreen.enemies[i];
                if (pv.entity.isAlive()) {
                    pv.isTarget = true;
                    can = true;
                    if (pv.entity.hasStatus("Provoke")) break;
                }
            }
        } else if (target == SkillTarget.PLAYER) {
            for (PlayerBattleView pv : Labyrintale.battleScreen.players) {
                if (pv.entity.isAlive()) {
                    pv.isTarget = true;
                    can = true;
                }
            }
        }
        return can;
    }

    public enum SkillType {
        ATTACK,
        DEFENCE,
        SCHEME,
        MOVE
    }

    public enum SkillRarity {
        STARTER,
        NORMAL,
        SPECIAL,
        BASIC,
        ADVISOR,
        ENEMY
    }

    public enum SkillTarget {
        NONE,
        ALL,
        SELF,
        RIGHT,
        LEFT,
        BOTH,
        SELF_RIGHT,
        SELF_LEFT,
        SELF_BOTH,
        PLAYER_FIRST,
        ENEMY_FIRST,
        PLAYER_LAST,
        ENEMY_LAST,
        PLAYER_FIRST_TWO,
        ENEMY_FIRST_TWO,
        PLAYER_LAST_TWO,
        ENEMY_LAST_TWO,
        PLAYER_ALL,
        ENEMY_ALL,
        ENEMY,
        PLAYER,
        PLAYER_LOW_HP,
        PLAYER_HIGH_HP,
        ENEMY_LOW_HP,
        ENEMY_HIGH_HP,
        OTHER
    }

    public enum IntentType {
        ATTACK,
        ATTACK_SHIELD,
        ATTACK_DEBUFF,
        ATTACK_BUFF,
        SHIELD,
        SHIELD_DEBUFF,
        SHIELD_BUFF,
        DEBUFF,
        BUFF
    }
}
