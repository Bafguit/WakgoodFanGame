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
import com.fastcat.labyrintale.skills.player.wak.Test73;
import com.fastcat.labyrintale.skills.player.wak.Test74;

import static com.badlogic.gdx.graphics.Color.FOREST;

public class Wakgood extends AbstractPlayer {

    private static final String ID = "wak";
    private static final int HEALTH = 26;
    private static final Color COLOR = FOREST;

    public Wakgood() {
        super(ID, HEALTH, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Test73(this));
        temp.add(new Test74(this));
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
