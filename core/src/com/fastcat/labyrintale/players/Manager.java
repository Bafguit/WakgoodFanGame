package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.Item1;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.manager.Outburst;

import static com.badlogic.gdx.graphics.Color.PINK;

public class Manager extends AbstractPlayer {

    private static final String ID = "manager";
    private static final int HEALTH = 25;
    private static final Color COLOR = PINK;

    public Manager() {
        super(ID, HEALTH, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Outburst(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new Item1(this));
        temp.add(new Item1(this));
        return temp;
    }
}
