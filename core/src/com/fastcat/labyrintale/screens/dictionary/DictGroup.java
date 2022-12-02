package com.fastcat.labyrintale.screens.dictionary;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.uis.control.InfoPanel;

public abstract class DictGroup {

    public InfoPanel.InfoType type;
    public AbstractSkill skill;
    public AbstractItem item;

    public void update() {}

    public final void setSkill(AbstractSkill skill) {
        this.skill = skill;
        item = null;
        type = InfoPanel.InfoType.SKILL;
    }

    public final void setItem(AbstractItem item) {
        this.item = item;
        skill = null;
        type = InfoPanel.InfoType.ITEM;
    }
}
