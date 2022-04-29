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
import com.fastcat.labyrintale.skills.player.lilpa.Lilpaa;
import com.fastcat.labyrintale.skills.player.lilpa.Test42;

import static com.badlogic.gdx.graphics.Color.NAVY;
import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class Lilpa extends AbstractPlayer {

    private static final String ID = "lilpa";
    private static final int HEALTH = 22;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;
    private static final Color COLOR = NAVY;

    public Lilpa() {
        super(ID, HEALTH, ATLAS, JSON, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Lilpaa(this));
        temp.add(new Test42(this));
        return temp;
    }
}
