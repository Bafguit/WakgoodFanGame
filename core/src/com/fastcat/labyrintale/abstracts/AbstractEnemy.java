package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class AbstractEnemy extends AbstractEntity {

    public EnemyType type;

    public AbstractEnemy(String id, EnemyType type, int maxHealth, TextureAtlas atlas, FileHandle skel) {
        super(id, EntityType.ENEMY, 1, maxHealth, atlas, skel);
        this.type = type;
        skeleton.setFlip(true, false);
    }

    public enum EnemyType {
        NORMAL, ELITE, BOSS
    }
}
