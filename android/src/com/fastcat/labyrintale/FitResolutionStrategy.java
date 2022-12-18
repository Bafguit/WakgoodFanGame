package com.fastcat.labyrintale;

import android.view.View;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;

public class FitResolutionStrategy implements ResolutionStrategy {

	@Override
	public MeasuredDimension calcMeasures (int widthMeasureSpec, int heightMeasureSpec) {

		final int height, width;
		int w = View.MeasureSpec.getSize(widthMeasureSpec), h = View.MeasureSpec.getSize(heightMeasureSpec);
		float a = ((float) h) / ((float) w);

		if(a <= 0.5625f) {
			height = h;
			width = (int) (2560 * ((float) h / 1440.0f));
		} else {
			width = w;
			height = (int) (1440 * ((float) w / 2560.0f));
		}

		return new MeasuredDimension(width, height);
	}
}