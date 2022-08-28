package com.fastcat.labyrintale.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.items.starter.BattleAxe;
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
        stat.debuRes = 10;
        stat.neutRes = 10;
        stat.critical = 10;
        stat.moveRes = 10;
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
        temp.add(new ShoulderPlate(this));
        temp.add(new BattleAxe(this));
        return temp;
    }
}
