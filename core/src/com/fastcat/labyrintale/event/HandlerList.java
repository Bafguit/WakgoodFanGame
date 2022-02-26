package com.fastcat.labyrintale.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HandlerList {
    private final List<RegisteredListener> registeredListeners = new ArrayList<>();

    public HandlerList() {
    }

    public List<RegisteredListener> getRegisteredListeners() {
        return registeredListeners;
    }

    public void registerListener(RegisteredListener listener) {
        this.registeredListeners.add(listener);
    }

    public void unregisterListener(RegisteredListener listener) {
        this.registeredListeners.remove(listener);
    }

    public void unregisterAll(Collection<RegisteredListener> listeners) {
        this.registeredListeners.removeAll(listeners);
    }

    public void registerAll(Collection<RegisteredListener> listeners) {
        this.registeredListeners.addAll(listeners);
    }
}
