package com.fastcat.labyrintale.players;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.player.Barrier;
import com.fastcat.labyrintale.skills.player.Heal;
import com.fastcat.labyrintale.skills.player.Light;
import com.fastcat.labyrintale.skills.player.Strike;

import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class Wakgood extends AbstractPlayer {

    private static final String ID = "wak";
    private static final int HEALTH = 25;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;

    public Wakgood() {
        super(ID, HEALTH, ATLAS, JSON);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        Strike s = new Strike(this);
        s.upgrade();
        temp.add(s);
        temp.add(new Light(this));
        temp.add(new Barrier(this));
        temp.add(new Heal(this));
        temp.add(new Strike(this));
        temp.add(new Light(this));
        temp.add(new Barrier(this));
        temp.add(new Heal(this));
        temp.add(new Strike(this));
        temp.add(new Light(this));
        temp.add(new Barrier(this));
        temp.add(new Heal(this));
        temp.add(new Strike(this));
        temp.add(new Light(this));
        temp.add(new Barrier(this));
        temp.add(new Heal(this));
        return temp;
    }
}
