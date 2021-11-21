package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.handlers.FontHandler;

public abstract class AbstractTalent implements Cloneable {

    private String id;

    public Texture img;
    public AbstractImage imageBox;
    public SkillTarget target;
    public String name;
    public String description;
    public boolean hasValue;
    public boolean negative;
    public int value;
    public int baseValue;

    public AbstractTalent(String id, SkillTarget target) {
        this(id, target, -1);
    }

    public AbstractTalent(String id, SkillTarget target, int value) {
        this.id = id;
        /** 여기에 json 받아오는거 입력 */
        /** 여기에 아틀라스 이미지 불러오는거 입력 */
        this.baseValue = value;
        this.value = this.baseValue;
        this.hasValue = this.value == -1 ? false : true;
        this.target = target;
        this.negative = false;
    }

    public String getKeyValue(String key) {
        switch (key) {
            default:
                return Integer.toString(this.value);
        }
    }

    public String getKeyColor(String key) {
        switch (key) {
            default:
                return FontHandler.getHexColor(valueColor());
        }
    }

    private Color valueColor() {
        if(value < baseValue) {
            return Color.SCARLET;
        } else if(value > baseValue) {
            return Color.CHARTREUSE;
        } else {
            return Color.CYAN;
        }
    }

    public final AbstractTalent cpy() {
        try {
            return (AbstractTalent) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum SkillTarget {
        NONE, SELF, ENEMY, ALL_ENEMY, ALL, RANDOM_ENEMY, RANDOM_ALL
    }
}
