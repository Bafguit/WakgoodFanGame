package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.FireStaff;
import com.fastcat.labyrintale.items.starter.FlameBook;
import com.fastcat.labyrintale.items.starter.Item1;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.lilpa.Lilpaa;
import com.fastcat.labyrintale.skills.player.lilpa.FireBall;

import static com.badlogic.gdx.graphics.Color.NAVY;

public class Lilpa extends AbstractPlayer {

    private static final String ID = "lilpa";
    private static final int HEALTH = 22;
    private static final Color COLOR = NAVY;

    public Lilpa() {
        super(ID, HEALTH, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Lilpaa(this));
        temp.add(new FireBall(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new FlameBook(this));
        temp.add(new FireStaff(this));
        return temp;
    }
}
