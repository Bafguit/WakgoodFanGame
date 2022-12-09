package com.fastcat.labyrintale.interfaces;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.uis.StatIcon;

public interface GetSelectedStat {
    void statSelected(AbstractEntity entity, StatIcon.StatType stat);
}
