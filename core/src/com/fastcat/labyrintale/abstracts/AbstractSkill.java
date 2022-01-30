package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.strings.SkillString;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.skillImg;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

public abstract class AbstractSkill implements Cloneable {

    public Sprite img;
    public Sprite imgBig;
    public SkillString.SkillData skillData;
    public AbstractEntity owner;
    public final SkillRarity rarity;
    public final SkillType type;
    public SkillTarget target;
    public String id;
    public String name;
    public String desc;
    public boolean upgraded = false;
    public boolean isTrick = false;
    public boolean isDispose = false;
    public boolean removable = true;
    public boolean canUse = true;
    public float uid;
    public int upgradeCount = 0;
    public int attack = 0;
    public int baseAttack = 0;
    public int spell = 0;
    public int baseSpell = 0;
    public int value = -1;
    public int baseValue = -1;

    public AbstractSkill(AbstractEntity owner, String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        uid = getUid();
        this.owner = owner;
        this.id = id;
        this.img = skillImg.get(this.id);
        this.imgBig = FileHandler.skillImgBig.get(this.id);
        this.skillData = StringHandler.skillString.get(this.id);
        this.name = this.skillData.NAME;
        this.desc = this.skillData.DESC;
        this.type = type;
        this.rarity = rarity;
        this.target = target;
    }

    public AbstractSkill(String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        this(null, id, type, rarity, target);
    }

    protected final void bot(AbstractAction a) {
        ActionHandler.bot(a);
    }

    public void setBaseAttack(int i) {
        this.attack = i;
        this.baseAttack = this.attack;
    }

    public void setBaseSpell(int i) {
        this.spell = i;
        this.baseSpell = this.spell;
    }

    public void setBaseValue(int i) {
        this.value = i;
        this.baseValue = this.value;
    }

    public void update() {

    }

    public void render(SpriteBatch sb) {

    }

    public String getKeyValue(String key) {
        switch (key) {
            case "A":
                return Integer.toString(this.attack);
            case "S":
                return Integer.toString(this.spell);
            case "V":
                return Integer.toString(this.value);
            default:
                return "ERROR_UNIDENTIFIABLE";
        }
    }

    public String getKeyColor(String key) {
        switch (key) {
            case "A":
                return getHexColor(valueColor(attack, baseAttack));
            case "S":
                return getHexColor(valueColor(spell, baseSpell));
            case "V":
                return getHexColor(valueColor(value, baseValue));
            default:
                return getHexColor(Color.WHITE);
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

    public static Array<AbstractEntity> getTargets(AbstractSkill s) {
        if(s.target == SkillTarget.SELF) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(s.owner);
            return temp;
        } else return getTargets(s.target);
    }

    public static Array<AbstractEntity> getTargets(AbstractStatus s) {
        if(s.target == SkillTarget.SELF) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(s.owner);
            return temp;
        } else return getTargets(s.target);
    }

    public static Array<AbstractEntity> getTargets(SkillTarget target) {
        Array<AbstractEntity> temp = new Array<>();
        PlayerView[] tp = battleScreen.players;
        EnemyView[] te = battleScreen.enemies;
        switch(target) {
            case NONE:
                break;
            case P_F:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case E_F:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case P_L:
                for(int i = 3; i >= 0; i--) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case E_L:
                for(int i = 3; i >= 0; i--) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case P_DF:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                    if(temp.size == 2) break;
                }
                break;
            case E_DF:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                    if(temp.size == 2) break;
                }
                break;
            case P_DL:
                for(int i = 3; i >= 0; i--) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                    if(temp.size == 2) break;
                }
                break;
            case E_DL:
                for(int i = 3; i >= 0; i--) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                    if(temp.size == 2) break;
                }
                break;
            case P_ALL:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                }
                break;
            case E_ALL:
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
        if(rarity == SkillRarity.ADVISOR) {
            battleScreen.advisor.canClick = false;
        }
        use();
    }

    protected abstract void use();

    public void upgrade() {
        upgradeCard();
        name += "+";
        upgraded = true;
        upgradeCount++;
    }

    protected abstract void upgradeCard();

    public final AbstractSkill cpy() {
        try {
            return (AbstractSkill) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum SkillType {
        ATTACK, DEFENCE, SCHEME
    }

    public enum SkillRarity {
        STARTER, BRONZE, SILVER, GOLD, SPECIAL, TOKEN, ADVISOR
    }

    public enum SkillTarget {
        NONE, SELF, P_F, E_F, P_L, E_L, P_DF, E_DF, P_DL, E_DL, P_ALL, E_ALL, ALL
    }
}
