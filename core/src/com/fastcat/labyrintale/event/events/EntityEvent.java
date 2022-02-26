package com.fastcat.labyrintale.event.events;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.event.Event;

public abstract class EntityEvent extends Event {

    public abstract AbstractEntity getEntity();
}
