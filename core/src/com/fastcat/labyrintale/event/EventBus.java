package com.fastcat.labyrintale.event;

import com.fastcat.labyrintale.event.listener.EventListener;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class EventBus {
    private static EventBus instance;

    private EventBus() {
    }

    public static EventBus getInstance() {
        if (instance == null)
            return (instance = new EventBus());
        return instance;
    }


    public void callEvent(Event event) {
        List<RegisteredListener> listeners = event.getHandlerList().getRegisteredListeners();
        for (RegisteredListener listener : listeners) {
            listener.fireEvent(event);
        }
    }

    public void registerListener(Class<? extends Event> event, EventListener listener) {
        Objects.requireNonNull(getRegisteredListeners(event)).registerListener(new RegisteredListener(listener));
    }

    private HandlerList getRegisteredListeners(Class<? extends Event> event) {
        try {
            Method m = getRegistrationClass(event).getDeclaredMethod("getHandlers");
            m.setAccessible(true);
            return (HandlerList) m.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private Class<? extends Event> getRegistrationClass(Class<? extends Event> clazz) {
        try {
            clazz.getDeclaredMethod("getHandlers");
            return clazz;
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null
                    && !clazz.getSuperclass().equals(Event.class)
                    && Event.class.isAssignableFrom(clazz.getSuperclass())) {
                return getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
            } else {
                throw new IllegalStateException("Unable to find handler list for event " + clazz.getName());
            }
        }
    }
}
