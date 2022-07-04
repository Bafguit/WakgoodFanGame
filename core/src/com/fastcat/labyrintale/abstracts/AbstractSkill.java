package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.strings.SkillString;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.skillImg;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

public abstract class AbstractSkill implements Cloneable, GetSelectedTarget {

    public Sprite img;
    public SkillString.SkillData skillData;
    public AbstractEntity owner;
    public final SkillType type;
    public SkillTarget target;
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
        this.img = skillImg.get(this.id);
        this.skillData = StringHandler.skillString.get(this.id);
        this.name = this.skillData.NAME;
        this.desc = this.skillData.DESC;
        this.type = type;
        this.target = target;
    }

    public AbstractSkill(String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        this(null, id, type, rarity, target);
    }

    protected final void top(AbstractAction a) {
        ActionHandler.top(a);
    }

    protected final void bot(AbstractAction a) {
        ActionHandler.bot(a);
    }

    public void setBaseAttack(int i) {
        attack = i;
        baseAttack = attack;
    }

    public void setBaseSpell(int i) {
        spell = i;
        baseSpell = spell;
    }

    public void setBaseValue(int i) {
        value = i;
        baseValue = value;
    }

    public void setBaseAttack(int i, int up) {
        attack = i;
        baseAttack = attack;
        upAttack = up;
    }

    public void setBaseSpell(int i, int up) {
        spell = i;
        baseSpell = spell;
        upSpell = up;
    }

    public void setBaseValue(int i, int up) {
        value = i;
        baseValue = value;
        upValue = up;
    }

    public void update() {

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

    public String getKeyValue(String key) {
        switch (key) {
            case "A":
                int a = attack;
                if(owner != null) {
                    if(owner.isPlayer) {
                        for (AbstractItem m : owner.item) {
                            if (m != null) a = m.showAttack(a);
                        }
                    }
                    for (AbstractStatus s : owner.status) {
                        if (s != null) a = s.showAttack(a);
                    }
                    if(owner.isPlayer) {
                        for (AbstractItem m : owner.item) {
                            if (m != null) a *= m.showAttackMultiply(a);
                        }
                    }
                    for (AbstractStatus s : owner.status) {
                        if (s != null) a *= s.showAttackMultiply(a);
                    }
                }
                return getHexColor(valueColor(a, baseAttack)) + a;
            case "S":
                int p = spell;
                if(owner != null) {
                    if(owner.isPlayer) {
                        for (AbstractItem m : owner.item) {
                            if (m != null) p = m.showSpell(p);
                        }
                    }
                    for (AbstractStatus s : owner.status) {
                        if (s != null) p = s.showSpell(p);
                    }
                    if(owner.isPlayer) {
                        for (AbstractItem m : owner.item) {
                            if (m != null) p *= m.showSpellMultiply(p);
                        }
                    }
                    for (AbstractStatus s : owner.status) {
                        if (s != null) p *= s.showSpellMultiply(p);
                    }
                }
                return getHexColor(valueColor(p, baseSpell)) + p;
            case "V":
                return getHexColor(Color.CYAN) + value;
            default:
                return "ERROR_UNIDENTIFIABLE";
        }
    }

    private Color valueColor(int v, int base) {
        if(v < base) {
            return Color.SCARLET;
        } else if(v > base) {
            return Color.CHARTREUSE;
        } else {
            return Color.CYAN;
        }
    }

    public static Array<AbstractEntity> getTargets(AbstractEntity owner, SkillTarget target) {
        if(target == SkillTarget.SELF) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(owner);
            return temp;
        } else if(target == SkillTarget.RIGHT) {
            Array<AbstractEntity> temp = new Array<>();
            if(owner.isPlayer) {
                if(owner.tempIndex > 0) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex - 1];
                    if(tp.isAlive()) temp.add(tp);
                }
            } else {
                if(owner.tempIndex < 3) {
                    AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.tempIndex + 1];
                    if(te.isAlive()) temp.add(te);
                }
            }
            return temp;
        } else if(target == SkillTarget.LEFT) {
            Array<AbstractEntity> temp = new Array<>();
            if(owner.isPlayer) {
                if(owner.tempIndex < 3) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex + 1];
                    if(tp.isAlive()) temp.add(tp);
                }
            } else {
                if(owner.tempIndex > 0) {
                    AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.tempIndex - 1];
                    if(te.isAlive()) temp.add(te);
                }
            }
            return temp;
        } else if(target == SkillTarget.BOTH) {
            Array<AbstractEntity> temp = new Array<>();
            if(owner.isPlayer) {
                if(owner.tempIndex > 0) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex - 1];
                    if(tp.isAlive()) temp.add(tp);
                }
                if(owner.tempIndex < 3) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex + 1];
                    if(tp.isAlive()) temp.add(tp);
                }
            } else {
                if(owner.tempIndex < 3) {
                    AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.tempIndex + 1];
                    if(te.isAlive()) temp.add(te);
                }
                if(owner.tempIndex > 0) {
                    AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.tempIndex - 1];
                    if(te.isAlive()) temp.add(te);
                }
            }
            return temp;
        } else if(target == SkillTarget.SELF_RIGHT) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(owner);
            if(owner.isPlayer) {
                if(owner.tempIndex > 0) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex - 1];
                    if(tp.isAlive()) temp.add(tp);
                }
            } else {
                if(owner.tempIndex < 3) {
                    AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.tempIndex + 1];
                    if(te.isAlive()) temp.add(te);
                }
            }
            return temp;
        } else if(target == SkillTarget.SELF_LEFT) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(owner);
            if(owner.isPlayer) {
                if(owner.tempIndex < 3) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex + 1];
                    if(tp.isAlive()) temp.add(tp);
                }
            } else {
                if(owner.tempIndex > 0) {
                    AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.tempIndex - 1];
                    if(te.isAlive()) temp.add(te);
                }
            }
            return temp;
        } else if(target == SkillTarget.SELF_BOTH) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(owner);
            if(owner.isPlayer) {
                if(owner.tempIndex > 0) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex - 1];
                    if(tp.isAlive()) temp.add(tp);
                }
                if(owner.tempIndex < 3) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex + 1];
                    if(tp.isAlive()) temp.add(tp);
                }
            } else {
                if(owner.tempIndex < 3) {
                    AbstractPlayer tp = AbstractLabyrinth.players[owner.tempIndex + 1];
                    if(tp.isAlive()) temp.add(tp);
                }
                if(owner.tempIndex > 0) {
                    AbstractEnemy te = AbstractLabyrinth.currentFloor.currentRoom.enemies[owner.tempIndex - 1];
                    if(te.isAlive()) temp.add(te);
                }
            }
            return temp;
        } else return getTargets(target);
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
        switch(target) {
            case NONE:
                break;
            case PLAYER_FIRST:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case ENEMY_FIRST:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case PLAYER_LAST:
                for(int i = 3; i >= 0; i--) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case ENEMY_LAST:
                for(int i = 3; i >= 0; i--) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case PLAYER_FIRST_TWO:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                    if(temp.size == 2) break;
                }
                break;
            case ENEMY_FIRST_TWO:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                    if(temp.size == 2) break;
                }
                break;
            case PLAYER_LAST_TWO:
                for(int i = 3; i >= 0; i--) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                    if(temp.size == 2) break;
                }
                break;
            case ENEMY_LAST_TWO:
                for(int i = 3; i >= 0; i--) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                    if(temp.size == 2) break;
                }
                break;
            case PLAYER_ALL:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                }
                break;
            case ENEMY_ALL:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                }
                break;
            case ALL:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    AbstractEnemy e = te[i].enemy;
                    if(p.isAlive()) temp.add(p);
                    if(e.isAlive()) temp.add(e);
                }
                break;
        }
        return temp;
    }

    public final void useCard() {
        if(owner != null) {
            for (AbstractStatus s : owner.status) {
                if (s != null) s.onUseCard(this);
            }
        }
        use();
        if(!isTrick && AbstractLabyrinth.energy > 0) AbstractLabyrinth.energy--;
        if(disposable) usedOnce = true;
        else cooldown = cooltime;
    }

    public final boolean canUse() {
        return cooldown == 0 && !usedOnce && !usedOnly && AbstractLabyrinth.energy > 0 && available() && (owner == null || (!owner.isNeut || type == SkillType.DEFENCE));
    }

    protected boolean available() {
        return true;
    }

    protected abstract void use();

    public void upgrade() {
        if(upgradable()) {
            upgradeCard();
            if (upAttack != -1) {
                baseAttack = attack + upAttack;
                attack = baseAttack;
            }
            if (upSpell != -1) {
                baseSpell = spell + upSpell;
                spell = baseSpell;
            }
            if (upValue != -1) {
                baseValue = value + upValue;
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

    public int onAttack(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
        return damage;
    }

    public int onAttacked(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        return damage;
    }

    public float onAttackMultiply(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
        return 1.0f;
    }

    public float onAttackedMultiply(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        return 1.0f;
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

    public void onTargetSelected(AbstractEntity target) {

    }

    public enum SkillType {
        ATTACK, DEFENCE, SCHEME
    }

    public enum SkillRarity {
        STARTER, BRONZE, SILVER, GOLD, SPECIAL, TOKEN, ADVISOR, ENEMY
    }

    public enum SkillTarget {
        NONE, ALL, SELF, RIGHT, LEFT, BOTH, SELF_RIGHT, SELF_LEFT, SELF_BOTH,
        PLAYER_FIRST, ENEMY_FIRST, PLAYER_LAST, ENEMY_LAST, PLAYER_FIRST_TWO,
        ENEMY_FIRST_TWO, PLAYER_LAST_TWO, ENEMY_LAST_TWO, PLAYER_ALL, ENEMY_ALL, ENEMY, PLAYER
    }
}
