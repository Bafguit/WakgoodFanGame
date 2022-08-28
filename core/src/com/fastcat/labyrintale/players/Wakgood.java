package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.OldShield;
import com.fastcat.labyrintale.items.starter.Pendant;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.wak.ShieldPush;
import com.fastcat.labyrintale.skills.player.wak.Wakchori;

import static com.badlogic.gdx.graphics.Color.FOREST;

public class Wakgood extends AbstractPlayer {

    private static final String ID = "wak";
    private static final int HEALTH = 26;
    private static final Color COLOR = FOREST;

    public Wakgood() {
        super(ID, HEALTH, COLOR);
        stat.debuRes = 10;
        stat.neutRes = 15;
        stat.critical = 5;
        stat.moveRes = 10;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        //temp.add(new Wakchori(this));
        temp.add(new ShieldPush(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new OldShield(this));
        temp.add(new Pendant(this));
        return temp;
    }
}
