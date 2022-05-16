package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class WeakEnemy2 extends AbstractEnemy {

    private static final String ID = "WeakEnemy2";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 8;

    public WeakEnemy2() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new AmbushE(this));
        temp.add(new CorrosiveE(this));
        return temp;
    }
}