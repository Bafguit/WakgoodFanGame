package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.actions.EndPlayerTurnAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.actions.SetAnimationAction;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.strings.SkillString;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

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
    public boolean upgraded = false;
    public boolean usedOnce = false;
    public boolean usedOnly = false;
    public boolean passive = false;
    public boolean disposable = false;
    public boolean isTrick = false;
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
    public int cooltime = 2;
    public int cooldown = 0;

    public AbstractSkill(AbstractEntity owner, String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        this.owner = owner;
        this.id = id;
        this.img = FileHandler.getSkillImg().get(this.id);
        this.skillData = StringHandler.skillString.get(this.id);
        this.name = this.skillData.NAME;
        this.desc = this.skillData.DESC;
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
            }
        }
        return getTargets(target);
    }

    //스킬 대상(유동)
    public static Array<AbstractEntity> getTargets(AbstractSkill s) {
        return getTargets(s.owner, s.target);
    }

    //상태 대상(유동)
    public static Array<AbstractEntity> getTargets(AbstractStatus s) {
        return getTargets(s.owner, s.target);
    }

    //고정 대상
    public static Array<AbstractEntity> getTargets(SkillTarget target) {
        Array<AbstractEntity> temp = new Array<>();
        PlayerView[] tp = battleScreen.players;
        EnemyView[] te = battleScreen.enemies;
        switch (target) {
            case NONE:
                break;
            case PLAYER_FIRST:
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case ENEMY_FIRST:
                for (int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if (e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case PLAYER_LAST:
                boolean has = false;
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive() && p.hasStatus("Provoke")) {
                        temp.add(p);
                        has = true;
                        break;
                    }
                }
                if (!has) {
                    for (int i = 3; i >= 0; i--) {
                        AbstractPlayer p = tp[i].player;
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
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive() && p.hasStatus("Provoke")) {
                        temp.add(p);
                        has = true;
                        break;
                    }
                }
                if (!has) {
                    for (int i = 3; i >= 0; i--) {
                        AbstractEnemy e = te[i].enemy;
                        if (e.isAlive()) {
                            temp.add(e);
                            break;
                        }
                    }
                }
                break;
            case PLAYER_FIRST_TWO:
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive()) temp.add(p);
                    if (temp.size == 2) break;
                }
                break;
            case ENEMY_FIRST_TWO:
                for (int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if (e.isAlive()) temp.add(e);
                    if (temp.size == 2) break;
                }
                break;
            case PLAYER_LAST_TWO:
                for (int i = 3; i >= 0; i--) {
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive()) temp.add(p);
                    if (temp.size == 2) break;
                }
                break;
            case ENEMY_LAST_TWO:
                for (int i = 3; i >= 0; i--) {
                    AbstractEnemy e = te[i].enemy;
                    if (e.isAlive()) temp.add(e);
                    if (temp.size == 2) break;
                }
                break;
            case PLAYER_ALL:
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive()) temp.add(p);
                }
                break;
            case ENEMY_ALL:
                for (int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if (e.isAlive()) temp.add(e);
                }
                break;
            case ALL:
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    AbstractEnemy e = te[i].enemy;
                    if (p.isAlive()) temp.add(p);
                    if (e.isAlive()) temp.add(e);
                }
                break;
            case PLAYER_LOW_HP:
                int low = 2147483647;
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive() && p.health < low) low = p.health;
                }
                for (PlayerView p : tp) {
                    if (p.player.health == low) temp.add(p.player);
                }
                break;
            case ENEMY_LOW_HP:
                low = 2147483647;
                for (int i = 0; i < 4; i++) {
                    AbstractEnemy p = te[i].enemy;
                    if (p.isAlive() && p.health < low) low = p.health;
                }
                for (EnemyView p : te) {
                    if (p.enemy.health == low) temp.add(p.enemy);
                }
                break;
            case PLAYER_HIGH_HP:
                low = 0;
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if (p.isAlive() && p.health > low) low = p.health;
                }
                for (PlayerView p : tp) {
                    if (p.player.health == low) temp.add(p.player);
                }
                break;
            case ENEMY_HIGH_HP:
                low = 0;
                for (int i = 0; i < 4; i++) {
                    AbstractEnemy p = te[i].enemy;
                    if (p.isAlive() && p.health > low) low = p.health;
                }
                for (EnemyView p : te) {
                    if (p.enemy.health == low) temp.add(p.enemy);
                }
                break;
        }
        return temp;
    }

    public static boolean noMoreSkill() {
        if (AbstractLabyrinth.advisor.skill.canUse()) return false;
        for (AbstractPlayer p : players) {
            for (AbstractSkill s : p.hand) {
                if (s.canUse() && !s.passive) return false;
            }
        }
        return true;
    }

    protected final void top(AbstractAction a) {
        ActionHandler.top(a);
    }

    protected final void bot(AbstractAction a) {
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

    public void update() {
        attack = calculateAttack(baseAttack);
        spell = calculateSpell(baseSpell);
        value = calculateValue(baseValue);
    }

    public void render(SpriteBatch sb) {

    }

    public final void flash() {
        //TODO 소리 추가
        flash(owner);
    }

    public final void flash(AbstractEntity e) {
        //TODO 소리 추가
        EffectHandler.add(new UpIconEffect(e.animX, e.animY + Gdx.graphics.getHeight() * 0.2f, img));
    }

    public int calculateAttack(int a) {
        return a;
    }

    public int calculateSpell(int s) {
        return s;
    }

    public int calculateValue(int v) {
        return v;
    }

    public String getKeyValue(String key) {
        switch (key) {
            case "A":
                int a = baseAttack;
                if (AbstractLabyrinth.cPanel != null) {
                    a = calculateAttack(a);
                    if (owner != null) {
                        if (owner.isPlayer) {
                            for (AbstractItem m : owner.item) {
                                if (m != null) a = m.showAttack(a);
                            }
                        }
                        for (AbstractStatus s : owner.status) {
                            if (s != null) a = s.showAttack(a);
                        }
                        if (owner.isPlayer) {
                            for (AbstractItem m : owner.item) {
                                if (m != null) a *= m.attackMultiply(a);
                            }
                        }
                        for (AbstractStatus s : owner.status) {
                            if (s != null) a *= s.attackMultiply();
                        }
                    }
                    if (AbstractLabyrinth.cPanel.type == ControlPanel.ControlType.BATTLE && battleScreen.looking.size == 1) {
                        AbstractEntity t = battleScreen.looking.get(0);
                        for (AbstractStatus s : t.status) {
                            if (s != null) a = s.showAttacked(a);
                        }
                        for (AbstractStatus s : t.status) {
                            if (s != null) a *= s.attackedMultiply();
                        }
                    }
                }
                a = Math.max(a, 0);
                return getHexColor(valueColor(a, baseAttack)) + a;
            case "S":
                int p = baseSpell;
                if (AbstractLabyrinth.cPanel != null) {
                    p = calculateSpell(p);
                    if (owner != null) {
                        if (owner.isPlayer) {
                            for (AbstractItem m : owner.item) {
                                if (m != null) p = m.showSpell(p);
                            }
                        }
                        for (AbstractStatus s : owner.status) {
                            if (s != null) p = s.showSpell(p);
                        }
                        if (owner.isPlayer) {
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
            case "C":
                return getHexColor(Color.CYAN) + cooltime;
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
        }
        if (target == SkillTarget.ENEMY || target == SkillTarget.PLAYER) {
            bot(new SelectTargetAction(this));
        } else {
            if(type == SkillType.DEFENCE || type == SkillType.SCHEME) {
                bot(new SetAnimationAction(owner, "skill"));
            }
            use();
            if (disposable) usedOnce = true;
            if (rarity == SkillRarity.ADVISOR || owner.isPlayer) {
                if (!isTrick && AbstractLabyrinth.energy > 0) AbstractLabyrinth.energy--;
                if (!disposable) cooldown = cooltime;
                if (AbstractLabyrinth.energy == 0 && noMoreSkill()) {
                    bot(new EndPlayerTurnAction());
                }
            }
        }
    }

    public final boolean canUse() {
        return cooldown == 0 && !usedOnce && !usedOnly && AbstractLabyrinth.energy > 0 && available();
    }

    protected boolean available() {
        return true;
    }

    protected abstract void use();

    public void upgrade() {
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
            upgraded = true;
            upgradeCount++;
            name = skillData.NAME + "+" + upgradeCount;
        }
    }

    public boolean upgradable() {
        return true;
    }

    protected abstract void upgradeCard();

    public void atBattleStart() {

    }

    public void atBattleEnd() {

    }

    public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {

    }

    public void onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {

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
        onTarget(target);
        if(type == SkillType.DEFENCE || type == SkillType.SCHEME) {
            top(new SetAnimationAction(owner, "skill"));
        }
        if (!isTrick && AbstractLabyrinth.energy > 0) AbstractLabyrinth.energy--;
        if (disposable) usedOnce = true;
        else cooldown = cooltime;
        if (AbstractLabyrinth.energy == 0 && noMoreSkill()) {
            bot(new EndPlayerTurnAction());
        }
    }

    public void onTarget(AbstractEntity target) {

    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        if (target == SkillTarget.ENEMY) {
            for (int i = 0; i < 4; i++) {
                EnemyView pv = Labyrintale.battleScreen.enemies[i];
                if (pv.enemy.isAlive()) {
                    pv.isTarget = true;
                    can = true;
                    if (pv.enemy.hasStatus("Provoke")) break;
                }
            }
        } else if (target == SkillTarget.PLAYER) {
            for (PlayerView pv : Labyrintale.battleScreen.players) {
                if (pv.player.isAlive()) {
                    pv.isTarget = true;
                    can = true;
                }
            }
        }
        return can;
    }

    public enum SkillType {
        ATTACK, DEFENCE, SCHEME, MOVE
    }

    public enum SkillRarity {
        STARTER, NORMAL, SPECIAL, BASIC, ADVISOR, ENEMY
    }

    public enum SkillTarget {
        NONE, ALL, SELF, RIGHT, LEFT, BOTH, SELF_RIGHT, SELF_LEFT, SELF_BOTH,
        PLAYER_FIRST, ENEMY_FIRST, PLAYER_LAST, ENEMY_LAST, PLAYER_FIRST_TWO,
        ENEMY_FIRST_TWO, PLAYER_LAST_TWO, ENEMY_LAST_TWO, PLAYER_ALL, ENEMY_ALL,
        ENEMY, PLAYER, PLAYER_LOW_HP, PLAYER_HIGH_HP, ENEMY_LOW_HP, ENEMY_HIGH_HP
    }
}
