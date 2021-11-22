package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.buttons.CardUIButton;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.strings.CardString;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

public abstract class AbstractSkill implements Cloneable {

    public Texture img;
    public CardString.CardData cardData;
    public PlayerClass playerClass;
    public CardType type;
    public CardRarity rarity;
    public CardTarget target;
    public String id;
    public String name;
    public String cond;
    public String desc;
    public boolean upgraded;
    public int upgradeCount;
    public int attack = 0;
    public int baseAttack = 0;
    public int spell = 0;
    public int baseSpell = 0;
    public int value = -1;
    public int baseValue = -1;
    public int cost;
    public int baseCost;

    public AbstractSkill(String id, Texture tex, PlayerClass playerClass, CardType type, CardRarity rarity, CardTarget target, int cost) {
        this.id = id;
        this.img = tex;
        this.cardData = StringHandler.cardString.get(this.id);
        this.name = this.cardData.NAME;
        this.cond = this.cardData.COND;
        this.desc = this.cardData.DESC;
        this.playerClass = playerClass;
        this.type = type;
        this.rarity = rarity;
        this.target = target;
        this.cost = cost;
        this.baseCost = this.cost;
        this.upgraded = false;
        this.upgradeCount = 0;
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

    private boolean viewValue() {
        return SettingHandler.alwaysValue;
    }

    public abstract boolean condition(AbstractSkill otherCard);

    public abstract void use();

    public abstract void upgrade();

    public final AbstractSkill cpy() {
        try {
            return (AbstractSkill) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum CardType {
        ATTACK, SPELL
    }

    public enum CardRarity {
        STARTER, BRONZE, SILVER, GOLD, SPECIAL, TOKEN
    }

    public enum CardTarget {
        NONE, PLAYER, ENEMY, ALL_ENEMY, ALL, RANDOM_ENEMY, RANDOM_ALL
    }
}
