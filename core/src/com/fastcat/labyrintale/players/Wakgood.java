package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.OldShield;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.wak.ShieldPush;

public class Wakgood extends AbstractPlayer {

    private static final String ID = "wak";
    private static final int HEALTH = 26;
    private static final Color COLOR = new Color(0x6be18fff);

    public Wakgood() {
        super(ID, HEALTH, COLOR, PlayerJob.GUARD);
        stat.speed = 1;
        stat.debuRes = 5;
        stat.critical = 10;
        stat.moveRes = 45;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new ShieldPush(this));
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
        return new OldShield(this);
    }
}
