package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.BurgerHat;
import com.fastcat.labyrintale.items.starter.OldArmour;
import com.fastcat.labyrintale.skills.player.basic.*;
import com.fastcat.labyrintale.skills.player.burger.Protect;
import com.fastcat.labyrintale.skills.player.burger.Strong;

import static com.badlogic.gdx.graphics.Color.YELLOW;

public class Burger extends AbstractPlayer {

    private static final String ID = "burger";
    private static final int HEALTH = 27;
    private static final Color COLOR = YELLOW;

    public Burger() {
        super(ID, HEALTH, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Protect(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new OldArmour(this));
        temp.add(new BurgerHat(this));
        return temp;
    }
}
