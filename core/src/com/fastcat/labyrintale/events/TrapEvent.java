package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.PlayerEventChoice;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.advisor;

public class TrapEvent extends AbstractEvent implements GetSelectedPlayer {

    private static final String ID = "Trap";
    private static final int SIZE = 3;
    private static final int DMG = 4;

    public TrapEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if(page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1, new EventCondition() {
                @Override
                public boolean condition() {
                    return advisor.cls == AdvisorClass.SOPHIA || advisor.cls == AdvisorClass.SECRET;
                }

                @Override
                public String cdText() {
                    return data.SELECT[1];
                }
            }));
            a.add(new PlayerEventChoice(data.SELECT[2], this));
        } else {
            a.add(new EndEventChoice(data.SELECT[3]));
        }
        return a;
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        player.takeDamage(new AbstractEntity.DamageInfo(null, 4, AbstractEntity.DamageType.LOSE));
        setPage(2);
        desc = player.name + data.DESC[2];
    }
}
