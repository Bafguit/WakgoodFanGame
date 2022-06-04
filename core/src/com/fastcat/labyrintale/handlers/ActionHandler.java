package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.interfaces.EventCallback;

public class ActionHandler {

    private static final Queue<AbstractAction> actionList = new Queue<>();
    public static final Array<EventCallback<AbstractAction>> listeners = new Array<>();
    public static boolean isRunning = false;

    private AbstractAction current;

    public ActionHandler() {

    }

    public void update() {
        if(actionList.size > 0 || current != null) {
            isRunning = true;
            if(current == null) {
                current = actionList.removeFirst();
            }
            if(!Labyrintale.fading) {
                current.update();
            }
            if(current.isDone) {
                current = null;
            }
        } else isRunning = false;
    }

    public void render(SpriteBatch sb) {

    }

    public static void clear() {
        actionList.clear();
    }

    public static void bot(AbstractAction action) {
        actionList.addLast(action);
    }

    public static void top(AbstractAction action) {
        actionList.addFirst(action);
    }
}
