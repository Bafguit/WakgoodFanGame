package com.fastcat.labyrintale.event.events.entity;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.event.HandlerList;
import com.fastcat.labyrintale.event.events.EntityEvent;

public class EntityDeathEvent extends EntityEvent {

    private static final HandlerList handlers = new HandlerList();

    private final AbstractEntity entity;

    private boolean cancelled = false;

    public EntityDeathEvent(AbstractEntity entity) {
        this.entity = entity;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public AbstractEntity getEntity() {
        return entity;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
