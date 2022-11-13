package com.fastcat.labyrintale.prototype.providers;

import com.fastcat.labyrintale.abstracts.AbstractEntity;

public final class MonsterStatProvider extends EntityStatProvider{

    @Override
    public String getConfigurationFileName() {
        return "Monster.csv";
    }

}
