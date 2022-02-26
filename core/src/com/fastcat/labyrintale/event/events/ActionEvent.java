package com.fastcat.labyrintale.event.events;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.event.Event;

import java.util.List;

public abstract class ActionEvent extends Event {
    public abstract AbstractEntity getActor();

    public abstract AbstractAction getAction();

    public abstract List<AbstractEntity> getTargets();
}
