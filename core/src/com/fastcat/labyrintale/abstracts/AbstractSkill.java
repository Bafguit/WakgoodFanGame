package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.buttons.CardUIButton;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.strings.CardString;

import java.util.Random;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.ActionHandler.*;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

public abstract class AbstractSkill implements Cloneable {

    public Sprite img;
    public CardString.CardData cardData;
    public AbstractEntity owner;
    public PlayerClass playerClass;
    public CardRarity rarity;
    public CardTarget target;
    public String id;
    public String name;
    public String desc;
    public boolean upgraded;
    public float uid;
    public int upgradeCount;
    public int attack = 0;
    public int baseAttack = 0;
    public int spell = 0;
    public int baseSpell = 0;
    public int value = -1;
    public int baseValue = -1;

    public AbstractSkill(AbstractEntity owner, String id, Sprite tex, PlayerClass playerClass, CardRarity rarity, CardTarget target) {
        uid = getUid();
        this.owner = owner;
        this.id = id;
        this.img = tex;
        this.cardData = StringHandler.cardString.get(this.id);
        this.name = this.cardData.NAME;
        this.desc = this.cardData.DESC;
        this.playerClass = playerClass;
        this.rarity = rarity;
        this.target = target;
        this.upgraded = false;
        this.upgradeCount = 0;
    }

    public AbstractSkill(String id, Sprite tex, PlayerClass playerClass, CardRarity rarity, CardTarget target) {
        this(null, id, tex, playerClass, rarity, target);
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

    public static Array<AbstractEntity> getTargets(CardTarget target) {
        Array<AbstractEntity> temp = new Array<>();
        PlayerView[] tp = battleScreen.players;
        EnemyView[] te = battleScreen.enemies;
        switch(target) {
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
        if(rarity == CardRarity.ADVISOR) {
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

    public enum CardRarity {
        STARTER, BRONZE, SILVER, GOLD, SPECIAL, TOKEN, ADVISOR
    }

    public enum CardTarget {
        NONE, P_F, E_F, P_L, E_L, P_DF, E_DF, P_DL, E_DL, P_ALL, E_ALL, ALL
    }
}
