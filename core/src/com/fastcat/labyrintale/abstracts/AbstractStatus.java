package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class AbstractStatus implements Cloneable {

    private String id;

    public Sprite img;
    public String name;
    public String desc;

    public AbstractStatus(String id, Sprite img) {
        this.id = id;
        this.img = img;
    }

    public void remove() {

    }

    public void onApply() {

    }

    public void onRemove() {

    }

    public void onUseCard(AbstractSkill card) {

    }

    public void atBattleEnd() {

    }

    public void onDamage(AbstractEntity actor, int damage) {

    }
}
