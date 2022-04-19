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
import com.fastcat.labyrintale.skills.player.viichan.ChainMail;
import com.fastcat.labyrintale.skills.player.viichan.DiaSword;

import static com.badlogic.gdx.graphics.Color.*;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class Viichan extends AbstractPlayer {

    private static final String ID = "viichan";
    private static final int HEALTH = 25;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;
    private static final Color COLOR = CHARTREUSE;

    public Viichan() {
        super(ID, HEALTH, ATLAS, JSON, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new DiaSword(this));
        temp.add(new ChainMail(this));
        return temp;
    }
}
