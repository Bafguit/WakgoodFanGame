package com.fastcat.labyrintale.players;

import static com.badlogic.gdx.graphics.Color.NAVY;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.FlameBook;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.lilpa.FireBall;

public class Lilpa extends AbstractPlayer {

    private static final String ID = "lilpa";
    private static final int HEALTH = 22;
    private static final Color COLOR = new Color(0x899cffff);

    public Lilpa() {
        super(ID, HEALTH, COLOR);
        stat.speed = 2;
        stat.debuRes = 20;
        stat.critical = 15;
        stat.moveRes = 5;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new FireBall(this));
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
        return new FlameBook(this);
    }
}
