package com.fastcat.labyrintale.events.first;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.items.special.GreenHeart;

public class SealedHeartEvent extends AbstractEvent implements GetSelectedPlayer {

    private static final String ID = "SealedHeart";
    private static final int SIZE = 6;

    public SealedHeartEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new ItemRewardEventChoice(data.SELECT[0], new GreenHeart(null), new EventCondition.True(), this, 1));
            a.add(new NextPageEventChoice(data.SELECT[4], this, 5));
        } else if (page == 1) {
            a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
            a.add(new NextPageEventChoice(data.SELECT[2], this, 3));
            a.add(new NextPageEventChoice(data.SELECT[3], this, 4));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        player.takeDamage(new AbstractEntity.DamageInfo(null, 5, AbstractEntity.DamageType.LOSE));
        setPage(2);
        desc = player.name + data.DESC[2];
    }

    @Override
    public void onSetPage(int page) {
        if (page == 2) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.modifyMaxHealth(-3);
            }
        } else if (page == 3) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (p.isAlive()) p.takeDamage(new AbstractEntity.DamageInfo(null, 5, AbstractEntity.DamageType.LOSE));
            }
        } else if (page == 4) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.stat.debuRes -= 5;
                p.stat.moveRes -= 5;
                p.stat.neutRes -= 5;
            }
        }
    }
}
