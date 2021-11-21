package com.fastcat.labyrintale.abstracts;

public abstract class AbstractStatus implements Cloneable {

    private String id;

    public String name;
    public String desc;
    public boolean isFixed;
    public boolean hasCount;
    public int count;

    public AbstractStatus(int count) {
        this.count = count;
        this.hasCount = this.count == -1 ? false : true;
    }

    public void adjustCount(int count) {
        int var1 = this.count + count;
        if(var1 <= 0) {
            /** 제거 */
        } else {
            this.count = var1;
        }
    }

    public abstract void onApply();

    public abstract void onRemove();

    public abstract void onUseCard(AbstractSkill card);

    public abstract void atBattleEnd();
}
