package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import java.util.Iterator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
        Labyrintale.getBaseScreen().getEffectHandler().effectList.addLast(e);
    }

    public void update() {
        if (effectList.size > 0) {
            Iterator<AbstractEffect> it = effectList.iterator();
            while (it.hasNext()) {
                AbstractEffect e = it.next();
                e.update();
                if (e.isDone) {
                    e.onRemove();
                    e.dispose();
                    it.remove();
                }
            }
        }
    }

    public void render(SpriteBatch sb) {
        for (AbstractEffect e : effectList) {
            e.render(sb);
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
