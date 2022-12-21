package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.BurgerHat;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.burger.Protect;

public class Burger extends AbstractPlayer {

    private static final String ID = "burger";
    private static final int HEALTH = 27;
    private static final Color COLOR = new Color(0xfff081ff);

    public Burger() {
        super(ID, HEALTH, COLOR, PlayerJob.GUARD);
        stat.speed = 1;
        stat.debuRes = 30;
        stat.critical = 5;
        stat.moveRes = 25;
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
        temp.add(new PlaceHolder(this));
        temp.add(new PlaceHolder(this));
        return temp;
    }

    @Override
    public AbstractItem getPassive() {
        return new BurgerHat(this);
    }
}
