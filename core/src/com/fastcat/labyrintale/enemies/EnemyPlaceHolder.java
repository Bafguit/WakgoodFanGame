package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class EnemyPlaceHolder extends AbstractEnemy {

    private static final String ID = "PlaceHolder";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 25;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;

    public EnemyPlaceHolder() {
        super(ID, TYPE, HEALTH, ATLAS, JSON);
        isDead = true;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new StrikeE(this));
        temp.add(new BarrierE(this));
        return temp;
    }
}