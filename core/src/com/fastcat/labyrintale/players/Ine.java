package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.BattleAxe;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.items.starter.ShoulderPlate;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.ine.Pruning;

public class Ine extends AbstractPlayer {

    private static final String ID = "ine";
    private static final int HEALTH = 25;
    private static final Color COLOR = Color.valueOf("#8a2be2");

    public Ine() {
        super(ID, HEALTH, COLOR);
        stat.speed = 5;
        stat.debuRes = 0.1f;
        stat.neutRes = 0.1f;
        stat.critical = 0.1f;
        stat.moveRes = 0.1f;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Pruning(this));
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
        return new BattleAxe(this);
    }
}
