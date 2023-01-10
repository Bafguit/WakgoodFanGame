package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.allskill.AllSkillScreen;
import java.util.HashMap;

public class ChaosEventChoice extends AbstractEvent.EventChoice implements GetSelectedSlot {

    private final AbstractEvent event;
    private final int toPage;

    public ChaosEventChoice(String t, AbstractEvent.EventCondition c, AbstractEvent event) {
        this(t, c, event, -1);
    }

    public ChaosEventChoice(String t, AbstractEvent.EventCondition c, AbstractEvent event, int page) {
        super(t, c);
        this.event = event;
        toPage = page;
    }

    @Override
    protected void onSelect() {
        Labyrintale.addTempScreen(new AllSkillScreen(this));
    }

    @SuppressWarnings("NewApi")
    @Override
    public void slotSelected(AbstractPlayer player, int index) {
        Array<AbstractSkill> s = new Array<>();
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            for (AbstractSkill sk : GroupHandler.SkillGroup.normalSkills.get(p.playerClass)) {
                if (!p.hasSkill(sk.id)) s.add(sk);
            }
        }
        AbstractSkill sk = s.get(AbstractLabyrinth.skillRandom.random(0, s.size - 1)).clone();
        sk.owner = player;
        HashMap<String, Boolean> temp = UnlockHandler.achvs.get(UnlockHandler.Unlocks.SKILL);
        if(temp.get(sk.id) == null) temp.put(sk.id, true);
        else if (!temp.get(sk.id)) temp.replace(sk.id, true);
        player.gainSkill(index, sk);
        if (toPage >= 0) {
            event.setPage(toPage);
        }
    }
}
