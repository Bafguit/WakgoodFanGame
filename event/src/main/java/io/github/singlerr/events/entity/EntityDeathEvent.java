package io.github.singlerr.events.entity;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import org.apringframework.eventbus.event.Event;
import org.apringframework.eventbus.event.ListenerList;
import org.apringframework.eventbus.listener.MarkListenerList;

public final class EntityDeathEvent extends Event {

    @MarkListenerList
    private static final ListenerList listenerList = new ListenerList();

    private AbstractEntity entity;

    public EntityDeathEvent(AbstractEntity entity){
        this.entity = entity;
    }

    public AbstractEntity getEntity() {
        return entity;
    }

    @Override
    public ListenerList getListenerList() {

        return listenerList;
    }
}
