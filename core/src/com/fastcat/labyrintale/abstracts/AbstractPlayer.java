package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.skills.player.MoveLeft;
import com.fastcat.labyrintale.skills.player.MoveRight;
import com.fastcat.labyrintale.strings.CharString;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public abstract class AbstractPlayer extends AbstractEntity {

    public final Color pColor;
    public final Color pColorW;
    public final Color pColorLG;
    public final Color pColorDG;
    public final PlayerClass playerClass;
    public int[] slot = new int[]{0, 0, 0};

    public AbstractPlayer(String id, int maxHealth, Color c) {
        super(id, 4, maxHealth, FileHandler.atlas.get(id), FileHandler.skeleton.get(id), true);
        this.playerClass = PlayerClass.valueOf(id.toUpperCase());
        CharString.CharData temp = StringHandler.charString.get(id);
        name = temp.NAME;
        desc = temp.DESC;
        pColor = c.cpy();
        pColorW = c.cpy().mul(0.827f, 0.827f, 0.827f, 1);
        pColorLG = c.cpy().mul(0.663f, 0.663f, 0.663f, 1);
        pColorDG = c.cpy().mul(0.5f, 0.5f, 0.5f, 1);
        mLeft = mLeftTemp = new MoveLeft(this);
        mRight = mRightTemp = new MoveRight(this);
        setImage(charImg.get(playerClass), charImgBig.get(playerClass), charBgImg.get(playerClass));
        imgTiny = charImgTiny.get(playerClass);
    }

    @Override
    public void newDeck() {
        hand = new AbstractSkill[3];
        for(int i = 0; i < 3; i++) {
            hand[i] = deck.get(i).clone();
        }
        mRightTemp = mRight.clone();
        mLeftTemp = mLeft.clone();
    }

    public void update() {

    }

    public void revive() {
        isDead = false;
        health = 1;
    }

    public void gainItem(AbstractItem i, int index) {
        item[index].onRemove();
        i.owner = this;
        item[index] = i;
        item[index].onGain();
    }

    public abstract Array<AbstractItem> getStartingItem();

    public enum PlayerClass {
        WAK, MANAGER, INE, VIICHAN, LILPA, BURGER, GOSEGU, JURURU
    }

    public static String getClassName(PlayerClass playerClass) {
        return playerClass.toString().toLowerCase();
    }
}
