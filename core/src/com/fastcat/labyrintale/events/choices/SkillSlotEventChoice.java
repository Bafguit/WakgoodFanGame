package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;

public class SkillSlotEventChoice extends AbstractEvent.EventChoice implements GetSelectedSlot {

    private final AbstractEvent event;
    private final int toPage;

    public SkillSlotEventChoice(String t, AbstractEvent.EventCondition c, AbstractEvent event) {
        this(t, c, event, -1);
    }

    public SkillSlotEventChoice(String t, AbstractEvent.EventCondition c, AbstractEvent event, int page) {
        super(t, c);
        this.event = event;
        toPage = page;
    }

    @Override
    protected void onSelect() {
        Labyrintale.addTempScreen(new SlotSelectScreen(this, SlotSelectScreen.SlotType.UPGRADE));
    }

    @Override
    public void slotSelected(AbstractPlayer player, int index) {
        if (toPage >= 0) {
            event.setPage(toPage);
        }
    }
}
