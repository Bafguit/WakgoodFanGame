package com.fastcat.labyrintale.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAnimation;
import com.fastcat.labyrintale.handlers.InputHandler;

public class SpineAnimation extends AbstractAnimation {
        public TextureAtlas atlas;
        public Skeleton skeleton;
        public AnimationState state;
        public AnimationStateData stateData;

        public SpineAnimation(TextureAtlas atlas, FileHandle skel) {
            this.atlas = atlas;
            SkeletonJson json = new SkeletonJson(atlas);
            json.setScale(0.7f * InputHandler.scale);
            SkeletonData skeletonData = json.readSkeletonData(skel);
            skeleton = new Skeleton(skeletonData);
            skeleton.setColor(Color.WHITE);
            skeleton.setPosition(-10000, -10000);
            stateData = new AnimationStateData(skeletonData);
            state = new AnimationState(stateData);
            resetAnimation();
        }

        @Override
        public void render(SpriteBatch sb, float x, float y) {
            state.update(Labyrintale.tick);
            state.apply(skeleton);
            state.getCurrent(0).setTimeScale(1.0f);
            skeleton.updateWorldTransform();
            skeleton.setPosition(x, y);
            skeleton.setColor(sb.getColor());
            sb.end();
            Labyrintale.psb.begin();
            Labyrintale.sr.draw(Labyrintale.psb, skeleton);
            Labyrintale.psb.end();
            sb.begin();
        }

        @Override
        public final void setAndIdle(String key) {
            AnimationState.TrackEntry e = state.setAnimation(0, key, false);
            state.addAnimation(0, "idle", true, 0.0F);
            e.setTimeScale(1.0f);
        }

        @Override
        public final void resetAnimation() {
            AnimationState.TrackEntry e = state.setAnimation(0, "idle", true);
            e.setTrackTime(MathUtils.random(0.0f, 1.0f));
            e.setTimeScale(1.0f);
        }

        @Override
        public void setSkin(String key) {
            skeleton.setSkin(key);
        }

    @Override
    public void setDie() {
        AnimationState.TrackEntry e = state.setAnimation(0, "die", false);
        e.setTimeScale(1.0f);
    }
}