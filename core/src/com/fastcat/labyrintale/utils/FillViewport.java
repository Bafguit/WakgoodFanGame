package com.fastcat.labyrintale.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class FillViewport extends ScalingViewport {
    public FillViewport (float worldWidth, float worldHeight) {
        super(Scaling.fillY, worldWidth, worldHeight);
    }

    public FillViewport (float worldWidth, float worldHeight, Camera camera) {
        super(Scaling.fillY, worldWidth, worldHeight, camera);
    }
}
