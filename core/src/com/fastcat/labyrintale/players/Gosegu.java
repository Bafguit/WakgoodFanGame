package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.items.starter.ToxicFlask;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.gosegu.RustyShard;

public class Gosegu extends AbstractPlayer {

    private static final String ID = "gosegu";
    private static final int HEALTH = 21;
    private static final Color COLOR = new Color(0x87fffdff);

    public Gosegu() {
        super(ID, HEALTH, COLOR, PlayerJob.SUP);
        stat.speed = 2;
        stat.debuRes = 30;
        stat.critical = 5;
        stat.moveRes = 5;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new RustyShard(this));
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
        return new ToxicFlask(this);
    }
}
