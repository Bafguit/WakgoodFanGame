package com.fastcat.labyrintale.abstracts;

public abstract class AbstractAdvisor {

    public AdvisorClass cls;

    public AbstractAdvisor() {

    }

    public enum AdvisorClass {
        NONE, BURGER
    }
}
