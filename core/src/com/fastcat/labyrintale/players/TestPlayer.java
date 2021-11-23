package com.fastcat.labyrintale.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractTalent;

import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_ATLAS;
import static com.fastcat.labyrintale.handlers.FileHandler.NEKO_JSON;

public class TestPlayer extends AbstractPlayer {

    private static final String ID = "TestPlayer";
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final int HEALTH = 100;
    private static final TextureAtlas ATLAS = NEKO_ATLAS;
    private static final FileHandle JSON = NEKO_JSON;

    public TestPlayer() {
        super(ID, CLASS, HEALTH);
        loadAnimation();
    }

    public void loadAnimation() {
        atlas = ATLAS;
        SkeletonJson json = new SkeletonJson(atlas);
        json.setScale(0.65f);
        SkeletonData skeletonData = json.readSkeletonData(JSON);
        skeleton = new Skeleton(skeletonData);
        skeleton.setColor(Color.WHITE);
        stateData = new AnimationStateData(skeletonData);
        state = new AnimationState(stateData);
        AnimationState.TrackEntry e = state.setAnimation(0, "Standby", true);
        e.setTimeScale(1.0f);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        return null;
    }
}
