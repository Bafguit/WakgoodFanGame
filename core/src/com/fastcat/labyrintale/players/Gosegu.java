package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.Sedative;
import com.fastcat.labyrintale.items.starter.ToxicFlask;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.gosegu.Provoke;
import com.fastcat.labyrintale.skills.player.gosegu.BioCloud;

import static com.badlogic.gdx.graphics.Color.BLUE;

public class Gosegu extends AbstractPlayer {

    private static final String ID = "gosegu";
    private static final int HEALTH = 21;
    private static final Color COLOR = BLUE;

    public Gosegu() {
        super(ID, HEALTH, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new BioCloud(this));
        temp.add(new Provoke(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new ToxicFlask(this));
        temp.add(new Sedative(this));
        return temp;
    }
}
