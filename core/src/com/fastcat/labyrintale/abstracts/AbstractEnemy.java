package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class AbstractEnemy extends AbstractEntity {

    public EnemyType type;

    public AbstractEnemy(String id, EnemyType type, int maxHealth, TextureAtlas atlas, FileHandle skel) {
        super(id, EntityType.ENEMY, maxHealth, atlas, skel);
        this.type = type;
    }

    public enum EnemyType {
        WEAK, STRONG, ELITE, BOSS
    }
}
