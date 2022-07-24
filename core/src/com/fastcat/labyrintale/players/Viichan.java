package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.FabricMail;
import com.fastcat.labyrintale.items.starter.OldSword;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.viichan.DiaSword;

import static com.badlogic.gdx.graphics.Color.CHARTREUSE;

public class Viichan extends AbstractPlayer {

    private static final String ID = "viichan";
    private static final int HEALTH = 23;
    private static final Color COLOR = CHARTREUSE;

    public Viichan() {
        super(ID, HEALTH, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new DiaSword(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new OldSword(this));
        temp.add(new FabricMail(this));
        return temp;
    }
}
