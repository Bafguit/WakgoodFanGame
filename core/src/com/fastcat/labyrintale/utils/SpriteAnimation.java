package com.fastcat.labyrintale.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAnimation;
import com.fastcat.labyrintale.handlers.InputHandler;

import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class SpriteAnimation extends AbstractAnimation {
        private final HashMap<String, Animation<Sprite>> animation = new HashMap<>();
        private Animation<Sprite> next;
        private Animation<Sprite> current;
        private boolean loop = true;
        private float elapsed = 0;

        public SpriteAnimation(FileHandle folder) {
            if(folder.exists() && folder.isDirectory()) {
                FileHandle f1 = folder.child("idle");
                if(f1.exists() && f1.isDirectory()) {
                    Array<Sprite> arr = new Array<>();
                    FileHandle[] list = f1.list();
                    for(int i = 0; i < list.length; i++) {
                        Texture t = new Texture(f1.child((i + 1) + ".png"));
                        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        Sprite s = new Sprite(t);
                        arr.add(s);
                    }
                    if(arr.size > 0) {
                        animation.put("idle", new Animation<>(0.067f, arr));
                    }
                }
                FileHandle f2 = folder.child("attack.png");
                if(f2.exists()) {
                    Array<Sprite> arr = new Array<>();
                    Texture t = new Texture(f2);
                    t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    Sprite s = new Sprite(t);
                    arr.add(s);
                    if(arr.size > 0) {
                        animation.put("attack", new Animation<>(0.68f, arr));
                    }
                }
                FileHandle f3 = folder.child("skill.png");
                if(f3.exists()) {
                    Array<Sprite> arr = new Array<>();
                    Texture t = new Texture(f3);
                    t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    Sprite s = new Sprite(t);
                    arr.add(s);
                    if(arr.size > 0) {
                        animation.put("skill", new Animation<>(0.68f, arr));
                    }
                }
                FileHandle f4 = folder.child("hit.png");
                if(f4.exists()) {
                    Array<Sprite> arr = new Array<>();
                    Texture t = new Texture(f4);
                    t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    Sprite s = new Sprite(t);
                    arr.add(s);
                    if(arr.size > 0) {
                        animation.put("hit", new Animation<>(0.68f, arr));
                    }
                }
                FileHandle f5 = folder.child("die.png");
                if(f5.exists()) {
                    Array<Sprite> arr = new Array<>();
                    Texture t = new Texture(f5);
                    t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    Sprite s = new Sprite(t);
                    arr.add(s);
                    if(arr.size > 0) {
                        animation.put("die", new Animation<>(0.68f, arr));
                    }
                }
            }
            resetAnimation();
        }

        @Override
        public void render(SpriteBatch sb, float x, float y) {
            if(current == null && next != null) {
                current = next;
                next = null;
            }

            if(current != null) {
                Sprite s = current.getKeyFrame(elapsed);
                sb.draw(s, x - 224 * scale, y - 35 * scale, 448 * scale, 448 * scale);
                if(current.isAnimationFinished(elapsed)) {
                    if(loop) {
                        elapsed = 0;
                    } else if(next != null) {
                        current = next;
                        elapsed = 0;
                        loop = true;
                        next = null;
                    } else {
                        current = null;
                        elapsed = 0;
                    }
                } else {
                    elapsed += Labyrintale.tick;
                }
            }
        }

        @Override
        public void setAndIdle(String key) {
            elapsed = 0;
            current = animation.get(key);
            loop = false;
            next = animation.get("idle");
        }

        @Override
        public void resetAnimation() {
            current = animation.get("idle");
            next = null;
            loop = true;
            elapsed = MathUtils.random(0.0f, 1.0f);
        }

        @Override
        public void setSkin(String key) {
            
        }

    @Override
    public void setDie() {
            elapsed = 0;
            current = animation.get("die");
            loop = true;
            next = null;
    }
}