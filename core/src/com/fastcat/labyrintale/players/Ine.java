package com.fastcat.labyrintale.players;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Heal;
import com.fastcat.labyrintale.skills.player.basic.Light;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.ine.EyeSting;

import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class Ine extends AbstractPlayer {

    private static final String ID = "ine";
    private static final int HEALTH = 25;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;
    private static final Color COLOR = Color.valueOf("#8a2be2");

    public Ine() {
        super(ID, HEALTH, ATLAS, JSON, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        Strike s = new Strike(this);
        s.upgrade();
        temp.add(s);
        temp.add(new EyeSting(this));
        temp.add(new Barrier(this));
        temp.add(new Heal(this));
        temp.add(new Strike(this));
        return temp;
    }
}
