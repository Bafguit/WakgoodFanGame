package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Iterator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EffectHandler {

    public static final ShapeRenderer shr = new ShapeRenderer();
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    public Queue<AbstractEffect> effectList = new Queue<>();
    public boolean isShaking = false;

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static EffectHandler newInstance() {
        return new EffectHandler();
    }

    public static void add(AbstractEffect e) {
        Labyrintale.getCurScreen().getEffectHandler().effectList.addLast(e);
    }

    public void render(SpriteBatch sb) {
        if (effectList.size > 0) {
            Iterator<AbstractEffect> it = effectList.iterator();
            while (it.hasNext()) {
                AbstractEffect e = it.next();
                e.render(sb);
                if (e.isDone) {
                    e.onRemove();
                    e.dispose();
                    it.remove();
                }
            }
        }
    }

    public void removeAll() {
        for (AbstractEffect e : effectList) {
            e.onRemove();
            e.dispose();
        }
        effectList.clear();
    }
}
