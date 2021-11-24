package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.buttons.CardUIButton;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.strings.CardString;

import java.util.Random;

import static com.fastcat.labyrintale.handlers.ActionHandler.*;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

public abstract class AbstractSkill implements Cloneable {

    public boolean[] target = new boolean[8];
    public Texture img;
    public CardString.CardData cardData;
    public AbstractEntity owner;
    public PlayerClass playerClass;
    public CardRarity rarity;
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

    public AbstractSkill(AbstractEntity owner, String id, Texture tex, PlayerClass playerClass, CardRarity rarity) {
        uid = Labyrintale.getUid();
        this.owner = owner;
        this.id = id;
        this.img = tex;
        this.cardData = StringHandler.cardString.get(this.id);
        this.name = this.cardData.NAME;
        this.desc = this.cardData.DESC;
        this.playerClass = playerClass;
        this.rarity = rarity;
        this.upgraded = false;
        this.upgradeCount = 0;
    }

    public AbstractSkill(String id, Texture tex, PlayerClass playerClass, CardRarity rarity) {
        this(null, id, tex, playerClass, rarity);
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

    protected void setTarget(boolean t1, boolean t2, boolean t3, boolean t4, boolean t5, boolean t6, boolean t7, boolean t8) {
        target[0] = t1;
        target[1] = t2;
        target[2] = t3;
        target[3] = t4;
        target[4] = t5;
        target[5] = t6;
        target[6] = t7;
        target[7] = t8;
    }

    protected void setPlayerTarget(boolean t1, boolean t2, boolean t3, boolean t4) {
        target[0] = t1;
        target[1] = t2;
        target[2] = t3;
        target[3] = t4;
        target[4] = false;
        target[5] = false;
        target[6] = false;
        target[7] = false;
    }

    protected void setEnemyTarget(boolean t1, boolean t2, boolean t3, boolean t4) {
        target[0] = false;
        target[1] = false;
        target[2] = false;
        target[3] = false;
        target[4] = t1;
        target[5] = t2;
        target[6] = t3;
        target[7] = t4;
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

    protected Array<AbstractEntity> getTargets() {
        Array<AbstractEntity> temp = new Array<>();
        for(int i = 0; i < 4; i++) {
            if(target[i]) temp.add(Labyrintale.battleScreen.players[i].player);
            if(target[i + 4]) temp.add(Labyrintale.battleScreen.enemies[i].enemy);
        }
        return temp;
    }

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

    public enum CardRarity {
        STARTER, BRONZE, SILVER, GOLD, SPECIAL, TOKEN
    }

    public enum CardTarget {
        NONE, PLAYER, ENEMY, ALL_ENEMY, ALL, RANDOM_ENEMY, RANDOM_ALL
    }
}
