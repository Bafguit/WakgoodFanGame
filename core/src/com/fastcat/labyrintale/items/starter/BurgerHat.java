package com.fastcat.labyrintale.items.starter;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.HealAction;

public class BurgerHat extends AbstractItem {

    private static final String ID = "BurgerHat";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public BurgerHat(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    public void endOfTurn() {
        Array<AbstractEntity> temp = new Array<>();
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            if (p.isAlive() && p.block > 0) temp.add(p);
        }
        if (temp.size > 0) {
            int h = 1 + owner.calculateSpell(0) / 2;
            if (h > 0) {
                top(new HealAction(null, temp, h));
            }
        }
    }
}
