package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.Bible;
import com.fastcat.labyrintale.items.starter.CottonNecklace;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.jururu.Pray;
import com.fastcat.labyrintale.skills.player.jururu.Scorn;

import static com.badlogic.gdx.graphics.Color.MAGENTA;

public class Jururu extends AbstractPlayer {

    private static final String ID = "jururu";
    private static final int HEALTH = 20;
    private static final Color COLOR = MAGENTA;

    public Jururu() {
        super(ID, HEALTH, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Scorn(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new Bible(this));
        temp.add(new CottonNecklace(this));
        return temp;
    }
}
