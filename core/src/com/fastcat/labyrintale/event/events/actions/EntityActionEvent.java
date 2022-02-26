package com.fastcat.labyrintale.event.events.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.event.HandlerList;
import com.fastcat.labyrintale.event.events.ActionEvent;
import com.fastcat.labyrintale.misc.SimpleEntity;

import java.util.List;

public class EntityActionEvent extends ActionEvent {

    private static final HandlerList handlers = new HandlerList();
    private final AbstractEntity actor;
    private final AbstractAction action;
    private final List<AbstractEntity> targets;
    private final List<SimpleEntity> targetsBefore;

    public EntityActionEvent(AbstractEntity actor, AbstractAction action, List<AbstractEntity> targets, List<SimpleEntity> targetsBefore) {
        this.actor = actor;
        this.action = action;
        this.targets = targets;
        this.targetsBefore = targetsBefore;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

    public List<SimpleEntity> getTargetsBefore() {
        return targetsBefore;
    }

    @Override
    public AbstractEntity getActor() {
        return actor;
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