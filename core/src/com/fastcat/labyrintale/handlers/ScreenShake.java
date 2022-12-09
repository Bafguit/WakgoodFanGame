//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fastcat.labyrintale.Labyrintale;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenShake {
    /***
     * Instance of screen shake.
     * Initialized on getInstance()
     */
    private float x = 0.0F;

    private float duration = 0.0F;
    private float startDuration = 0.0F;
    private float intensityValue;
    private float intervalSpeed;
    private boolean vertical;
    private float sw, sh;
    private int iw, ih;

    /***
     * Returns instance of screen shake, if not exist, create new.
     * @return instance of screen shake
     */
    public static ScreenShake newInstance() {
        return new ScreenShake();
    }

    public void shake(ScreenShake.ShakeIntensity intensity, ScreenShake.ShakeDur dur, boolean isVertical) {
        this.duration = this.getDuration(dur);
        this.startDuration = this.duration;
        this.intensityValue = this.getIntensity(intensity);
        this.vertical = isVertical;
        this.intervalSpeed = 0.3F;
    }

    public void rumble(float dur) {
        this.duration = dur;
        this.startDuration = dur;
        this.intensityValue = 10.0F;
        this.vertical = false;
        this.intervalSpeed = 0.7F;
    }

    public void mildRumble(float dur) {
        this.duration = dur;
        this.startDuration = dur;
        this.intensityValue = 3.0F;
        this.vertical = false;
        this.intervalSpeed = 0.7F;
    }

    public void update(FitViewport viewport) {
        sw = Gdx.graphics.getWidth();
        sh = Gdx.graphics.getHeight();
        iw = (int) sw;
        ih = (int) sh;
        if (this.duration != 0.0F) {
            this.duration -= Labyrintale.tick;
            if (this.duration < 0.0F) {
                this.duration = 0.0F;
                viewport.update(iw, ih);
                return;
            }

            if (SettingHandler.setting.shake) {
                float tmp = Interpolation.fade.apply(0.1F, this.intensityValue, this.duration / this.startDuration);
                this.x = MathUtils.cosDeg((float) (System.currentTimeMillis() % 360L) / this.intervalSpeed) * tmp;
                if (this.vertical) {
                    viewport.update(iw, (int) (sh + Math.abs(this.x)));
                } else {
                    viewport.update((int) (sw + this.x), ih);
                }
            }
        }
    }

    private float getIntensity(ScreenShake.ShakeIntensity intensity) {
        switch (intensity) {
            case LOW:
                return 20.0F * InputHandler.scale;
            case MED:
                return 50.0F * InputHandler.scale;
            default:
                return 100.0F * InputHandler.scale;
        }
    }

    private float getDuration(ScreenShake.ShakeDur dur) {
        switch (dur) {
            case SHORT:
                return 0.3F;
            case MED:
                return 0.5F;
            case LONG:
                return 1.0F;
            case XLONG:
                return 3.0F;
            default:
                return 1.0F;
        }
    }

    public enum ShakeDur {
        SHORT,
        MED,
        LONG,
        XLONG;

        ShakeDur() {}
    }

    public enum ShakeIntensity {
        LOW,
        MED,
        HIGH;

        ShakeIntensity() {}
    }
}
