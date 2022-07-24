package com.fastcat.labyrintale.events.second;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.items.special.CrackedHeart;

public class UpsetIdolEvent extends AbstractEvent {

    private static final String ID = "UpsetIdol";
    private static final int SIZE = 4;

    public UpsetIdolEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1, new EventCondition() {
                @Override
                public boolean condition() {
                    boolean has = false;
                    for (AbstractPlayer p : AbstractLabyrinth.players) {
                        if (p.item[0].id.equals("GreenHeart") || p.item[1].id.equals("GreenHeart")) {
                            has = true;
                            break;
                        }
                    }
                    return has;
                }

                @Override
                public String cdText() {
                    return data.SELECT[1];
                }
            }));
            a.add(new NextPageEventChoice(data.SELECT[2], this, 2));
            a.add(new NextPageEventChoice(data.SELECT[3], this, 3));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void onSetPage(int page) {
        if (page == 1) {
            AbstractPlayer player = null;
            int index = -1;
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (p.item[0].id.equals("GreenHeart")) {
                    player = p;
                    index = 0;
                    break;
                } else if (p.item[1].id.equals("GreenHeart")) {
                    player = p;
                    index = 1;
                    break;
                }
            }
            if (player != null) player.gainItem(new CrackedHeart(player), index);
        } else if (page == 2) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (p.isAlive()) {
                    p.modifyMaxHealth(2);
                    p.takeDamage(new AbstractEntity.DamageInfo(null, 7, AbstractEntity.DamageType.LOSE));
                }
            }
        } else if (page == 3) {
            AbstractLabyrinth.modifyBleak(20);
        }
    }
}
