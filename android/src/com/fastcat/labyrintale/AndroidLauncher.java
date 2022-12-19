package com.fastcat.labyrintale;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.FixedResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.SettingHandler;

import java.io.File;

public class AndroidLauncher extends AndroidApplication {

	private static final int REQ_CODE = 1990;

	private static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

	private static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 10;
		config.useImmersiveMode = true;

		config.resolutionStrategy = new FitResolutionStrategy();

		initialize(new Labyrintale(), config);
	}
}
