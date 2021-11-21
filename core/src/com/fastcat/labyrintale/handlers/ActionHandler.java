package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;

public class ActionHandler {

    private static Array<AbstractAction> actionList = new Array<>();
    private AbstractAction previous;
    private AbstractAction current;

    public ActionHandler() {

    }

    public static void bot(AbstractAction action) {
        actionList.add(action);
    }

    public static void top(AbstractAction action) {
        actionList.insert(0, action);
    }
}
