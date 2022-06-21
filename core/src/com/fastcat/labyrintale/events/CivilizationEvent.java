package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.*;

public class CivilizationEvent extends AbstractEvent {

    private static final String ID = "Civilization";
    private static final int SIZE = 3;

    public CivilizationEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        //TODO 좀 더 구체적으로 수정
        if(page == 0) {
            a.add(new SkillRewardEventChoice(data.SELECT[0], new EventCondition.True(), this, 1));
            a.add(new SkillUpgradeEventChoice(data.SELECT[1], new EventCondition.True(), this, 2));
        } else {
            a.add(new EndEventChoice(data.SELECT[2]));
        }
        return a;
    }
}
