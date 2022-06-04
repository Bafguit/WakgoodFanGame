package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;

import java.util.Iterator;

public class EffectHandler {

    public static final ShapeRenderer shr = new ShapeRenderer();
    public Queue<AbstractEffect> effectList = new Queue<>();
    public boolean isShaking = false;

    public EffectHandler() {

    }

    public void render(SpriteBatch sb) {
        if(effectList.size > 0) {
            while(! effectList.isEmpty()){
                AbstractEffect e = effectList.removeFirst();
                e.render(sb);
            }
            Iterator<AbstractEffect> it = effectList.iterator();
            while (it.hasNext()){
                AbstractEffect e = it.next();
                if(e.isDone){
                    e.onRemove();
                    it.remove();
                }
            }
        }
    }

    public static void add(AbstractEffect e) {
        Labyrintale.getCurScreen().effectHandler.effectList.addLast(e);
    }

    public void removeAll() {
        for(AbstractEffect e : effectList) {
            e.onRemove();
        }
        effectList.clear();
    }
}
