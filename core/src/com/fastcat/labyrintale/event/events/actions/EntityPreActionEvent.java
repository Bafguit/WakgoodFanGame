package com.fastcat.labyrintale.event.events.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.event.HandlerList;
import com.fastcat.labyrintale.event.events.ActionEvent;

import java.util.List;


public class EntityPreActionEvent extends ActionEvent {

    private static final HandlerList handlers = new HandlerList();
    private final AbstractEntity entity;
    private final AbstractAction action;
    private final List<AbstractEntity> targets;

    public EntityPreActionEvent(AbstractEntity entity, AbstractAction action, List<AbstractEntity> targets) {
        this.entity = entity;
        this.action = action;
        this.targets = targets;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public AbstractEntity getActor() {
        return entity;
    }

    @Override
    public AbstractAction getAction() {
        return action;
    }

    @Override
    public List<AbstractEntity> getTargets() {
        return targets;
    }

    @Override
    public HandlerList getHandlerList() {
        return handlers;
    }
}
