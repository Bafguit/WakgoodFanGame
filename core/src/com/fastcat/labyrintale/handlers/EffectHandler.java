package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;

public class EffectHandler {

    public static final ShapeRenderer shr = new ShapeRenderer();
    public Array<AbstractEffect> effectList = new Array<>();
    public boolean isShaking = false;

    public EffectHandler() {

    }

    public void render(SpriteBatch sb) {
        if(effectList.size > 0) {
            for(int i = 0; i < effectList.size; i++) {
                AbstractEffect e = effectList.get(i);
                e.render(sb);
                if(e.isDone) {
                    e.onRemove();
                    effectList.removeIndex(i);
                }
            }
        }
    }

    public static void add(AbstractEffect e) {
        Labyrintale.getCurScreen().effectHandler.effectList.add(e);
    }

    public void removeAll() {
        for(AbstractEffect e : effectList) {
            e.onRemove();
        }
        effectList.clear();
    }
}
