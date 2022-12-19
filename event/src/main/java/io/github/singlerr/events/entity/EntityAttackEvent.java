package io.github.singlerr.events.entity;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import org.apringframework.eventbus.event.Event;
import org.apringframework.eventbus.event.ListenerList;
import org.apringframework.eventbus.listener.MarkListenerList;

public final class EntityAttackEvent extends Event {

    @MarkListenerList
    private static final ListenerList listenerList = new ListenerList();

    private AbstractEntity damager;

    private AbstractEntity victim;

    public EntityAttackEvent(AbstractEntity damager, AbstractEntity victim){
        this.damager = damager;
        this.victim = victim;
    }

    public AbstractEntity getDamager() {
        return damager;
    }

    public AbstractEntity getVictim() {
        return victim;
    }

    @Override
    public ListenerList getListenerList() {
        return listenerList;
    }
}
