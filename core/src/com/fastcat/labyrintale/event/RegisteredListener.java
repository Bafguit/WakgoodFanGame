package com.fastcat.labyrintale.event;

import com.fastcat.labyrintale.event.listener.EventHandler;
import com.fastcat.labyrintale.event.listener.EventListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RegisteredListener {
    private final EventListener listener;

    public RegisteredListener(EventListener listener) {
        this.listener = listener;
    }

    public void fireEvent(Event event) {
        List<Method> eventMethods = new ArrayList<>();
        for (Method method : listener.getClass().getDeclaredMethods()) {

            if (method.isAnnotationPresent(EventHandler.class)) {
                Class<?>[] parameters = method.getParameterTypes();
                if (parameters.length == 1 && parameters[0].equals(event.getClass()))
                    eventMethods.add(method);
            }

        }

        for (Method m : eventMethods) {
            try {
                m.setAccessible(true);
                m.invoke(listener, event);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
    }
}
