package com.fastcat.labyrintale.abstracts;

public abstract class AbstractAction implements Cloneable {

    protected static final float DUR_DEFAULT = 1.0f;
    protected static final float DUR_FAST = 0.5f;

    public String id;
    public ActionType type;
    public AbstractEntity actor;
    public AbstractEntity victim;
    public boolean isDone = false;
    public float duration;

    public AbstractAction() {

    }

    public enum ActionType {
        NONE, BATTLE
    }
}
