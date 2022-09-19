package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.BurgerHat;
import com.fastcat.labyrintale.items.starter.OldArmour;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.burger.Protect;

import static com.badlogic.gdx.graphics.Color.YELLOW;

public class Burger extends AbstractPlayer {

    private static final String ID = "burger";
    private static final int HEALTH = 27;
    private static final Color COLOR = YELLOW;

    public Burger() {
        super(ID, HEALTH, COLOR);
        stat.speed = 5;
        stat.debuRes = 0.05f;
        stat.neutRes = 0.1f;
        stat.critical = 0.05f;
        stat.moveRes = 0.2f;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Protect(this));
        return temp;
    }

    @Override
    public Array<AbstractItem> getStartingItem() {
        Array<AbstractItem> temp = new Array<>();
        temp.add(new OldArmour(this));
        temp.add(new BurgerHat(this));
        return temp;
    }
}
