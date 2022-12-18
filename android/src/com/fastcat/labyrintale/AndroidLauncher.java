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

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
			if(checkSelfPermission(WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
				requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},REQ_CODE);
		}

		SettingHandler.settingFile = new File(getExternalFilesDir(null),"settings.json");
		SettingHandler.initialize();

		config.resolutionStrategy = new FitResolutionStrategy();

		initialize(new Labyrintale(), config);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if(requestCode != REQ_CODE)
			return;

		if(permissions.length == 0)
			return;

		if(permissions[0].equals(WRITE_EXTERNAL_STORAGE) && grantResults[0] != PackageManager.PERMISSION_DENIED || permissions[1].equals(READ_EXTERNAL_STORAGE) && grantResults[1] != PackageManager.PERMISSION_DENIED){
			//권한 요청이 수행되지 않았을 때
		}
	}
}
