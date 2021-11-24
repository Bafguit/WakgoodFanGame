package com.fastcat.labyrintale.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractTalent;
import com.fastcat.labyrintale.skills.player.Barrier;
import com.fastcat.labyrintale.skills.player.Heal;
import com.fastcat.labyrintale.skills.player.Light;
import com.fastcat.labyrintale.skills.player.Strike;

import java.util.Random;

import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class TestPlayer extends AbstractPlayer {

    private static final String ID = "TestPlayer";
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final int HEALTH = 25;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;

    public TestPlayer() {
        super(ID, CLASS, HEALTH, ATLAS, JSON);
        health = MathUtils.random(1, 25);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Light(this));
        temp.add(new Barrier(this));
        temp.add(new Heal(this));
        return temp;
    }
}
