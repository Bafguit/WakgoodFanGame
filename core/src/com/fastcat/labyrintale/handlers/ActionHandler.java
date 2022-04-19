package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.interfaces.EventCallback;

public class ActionHandler {

    private static final Array<AbstractAction> actionList = new Array<>();
    public static final Array<EventCallback<AbstractAction>> listeners = new Array<>();
    public static boolean isRunning = false;

    private AbstractAction current;

    public ActionHandler() {

    }

    public void update() {
        if(actionList.size > 0 || current != null) {
            isRunning = true;
            if(current == null) {
                current = actionList.get(0);
                actionList.removeIndex(0);
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
        actionList.add(action);
    }

    public static void top(AbstractAction action) {
        actionList.insert(0, action);
    }
}
