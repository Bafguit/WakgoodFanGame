package com.fastcat.labyrintale.players;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.player.basic.*;
import com.fastcat.labyrintale.skills.player.burger.Test4;
import com.fastcat.labyrintale.skills.player.burger.Test5;
import com.fastcat.labyrintale.skills.player.burger.Test6;
import com.fastcat.labyrintale.skills.player.burger.Test7;

import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class Burger extends AbstractPlayer {

    private static final String ID = "burger";
    private static final int HEALTH = 30;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;
    private static final Color COLOR = YELLOW;

    public Burger() {
        super(ID, HEALTH, ATLAS, JSON, COLOR);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new Strike(this));
        temp.add(new Barrier(this));
        temp.add(new Test4(this));
        temp.add(new Test7(this));
        return temp;
    }
}
