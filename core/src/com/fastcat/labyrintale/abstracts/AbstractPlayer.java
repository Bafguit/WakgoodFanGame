package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.skills.player.MoveLeft;
import com.fastcat.labyrintale.skills.player.MoveRight;
import com.fastcat.labyrintale.strings.CharString;

import java.util.Objects;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public abstract class AbstractPlayer extends AbstractEntity {

    public final Color pColor;
    public final Color pColorW;
    public final Color pColorLG;
    public final Color pColorDG;
    public final PlayerClass playerClass;
    public String[] flav;

    public AbstractPlayer(String id, int maxHealth, Color c) {
        super(id, 4, maxHealth, FileHandler.atlas.get(id), FileHandler.skeleton.get(id), true);
        this.playerClass = PlayerClass.valueOf(id.toUpperCase());
        CharString.CharData temp = StringHandler.charString.get(id);
        name = temp.NAME;
        desc = temp.DESC;
        flav = temp.FLAV;
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
        hand = new AbstractSkill[4];
        for(int i = 0; i < 4; i++) {
            hand[i] = Objects.requireNonNull(deck.get(i).clone());
        }
        mRightTemp = mRight.clone();
        mLeftTemp = mLeft.clone();
    }

    public void update() {

    }

    public void gainItem(AbstractItem i, int index) {
        AbstractItem it = item[index];
        if(it != null) it.onRemove();
        i.owner = this;
        i.onGain();
        item[index] = i;
    }

    public abstract Array<AbstractItem> getStartingItem();

    public enum PlayerClass {
        WAK, MANAGER, INE, VIICHAN, LILPA, BURGER, GOSEGU, JURURU
    }

    public static String getClassName(PlayerClass playerClass) {
        return playerClass.toString().toLowerCase();
    }
}
