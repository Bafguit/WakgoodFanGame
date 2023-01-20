package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SettingHandler {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    public static SettingData setting;

    public static FileHandle settingFile;

    public static void initialize() {
        setting = new SettingData();
        Preferences prefs = Gdx.app.getPreferences("WakestDungeon_Setting");
        settingFile = Gdx.files.local("setting.json");
        if (settingFile.exists()) {
            try {
                SettingData data = mapper.readValue(Gdx.files.local("setting.json").file(), SettingData.class);

                // 화면 모드 설정
                setting.screenMode = prefs.getInteger("screenMode", data.screenMode);
                setting.width = prefs.getInteger("width", data.width);
                setting.height = prefs.getInteger("height", data.height);

                // 볼륨 설정
                setting.volumeBgm = prefs.getInteger("volumeBgm", data.volumeBgm);
                setting.volumeSfx = prefs.getInteger("volumeSfx", data.volumeSfx);

                // 기타 설정
                setting.shake = prefs.getBoolean("shake", data.shake);
                setting.fastMode = prefs.getBoolean("fastMode", data.fastMode);

                // 튜토리얼
                setting.charTutorial = prefs.getBoolean("charTutorial", data.charTutorial);
                setting.battleTutorial = prefs.getBoolean("battleTutorial", data.battleTutorial);
                setting.rewardTutorial = prefs.getBoolean("rewardTutorial", data.rewardTutorial);
                setting.wayTutorial = prefs.getBoolean("wayTutorial", data.wayTutorial);


                setting.cartoon = new boolean[3];
                for(int i = 0; i < 3; i++) {
                    setting.cartoon[i] = prefs.getBoolean("cartoon_" + i, data.cartoon[i]);
                }
                setting.forceCredit = prefs.getBoolean("forceCredit" , data.forceCredit);

                if(data.skin != null) {
                    setting.skin = new HashMap<>();
                    for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
                        setting.skin.put(cls, prefs.getString("skin_" + cls, data.skin.get(cls)));
                    }
                } else {
                    setting.skin = new HashMap<>();
                    for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
                        setting.skin.put(cls, prefs.getString("skin_" + cls, "basic"));
                    }
                }
                settingFile.delete();
            } catch (IOException e) {
                load(prefs);
            }
        } else {
            load(prefs);
        }
        save();
    }

    public static void load(Preferences prefs) {
        // 화면 모드 설정
        setting.screenMode = prefs.getInteger("screenMode", 0);
        setting.width = prefs.getInteger("width", 1600);
        setting.height = prefs.getInteger("height", 900);

        // 볼륨 설정
        setting.volumeBgm = prefs.getInteger("volumeBgm", 80);
        setting.volumeSfx = prefs.getInteger("volumeSfx", 80);

        // 기타 설정
        setting.shake = prefs.getBoolean("shake", true);
        setting.fastMode = prefs.getBoolean("fastMode", false);

        // 튜토리얼
        setting.charTutorial = prefs.getBoolean("charTutorial", true);
        setting.battleTutorial = prefs.getBoolean("battleTutorial", true);
        setting.wayTutorial = prefs.getBoolean("wayTutorial", true);
        setting.rewardTutorial = prefs.getBoolean("rewardTutorial", true);

        setting.cartoon = new boolean[3];
        for(int i = 0; i < 3; i++) {
            setting.cartoon[i] = prefs.getBoolean("cartoon_" + i, true);
        }
        setting.forceCredit = prefs.getBoolean("forceCredit", true);

        setting.skin = new HashMap<>();
        for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
            setting.skin.put(cls, prefs.getString("skin_" + cls, "basic"));
        }
    }

    public static void save() {
        Preferences prefs = Gdx.app.getPreferences("WakestDungeon_Setting");
        prefs.putInteger("screenMode", setting.screenMode);
        prefs.putInteger("width", setting.width);
        prefs.putInteger("height", setting.height);
        prefs.putInteger("volumeBgm", setting.volumeBgm);
        prefs.putInteger("volumeSfx", setting.volumeSfx);
        prefs.putBoolean("shake", setting.shake);
        prefs.putBoolean("fastMode", setting.fastMode);
        prefs.putBoolean("charTutorial", setting.charTutorial);
        prefs.putBoolean("battleTutorial", setting.battleTutorial);
        prefs.putBoolean("wayTutorial", setting.wayTutorial);
        prefs.putBoolean("rewardTutorial", setting.rewardTutorial);
        for(int i = 0; i < 3; i++) {
            prefs.putBoolean("cartoon_" + i, setting.cartoon[i]);
        }
        prefs.putBoolean("forceCredit", setting.forceCredit);
        for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
            prefs.putString("skin_" + cls, setting.skin.get(cls));
        }
        prefs.flush();
    }

    public static class SettingData {
        public int volumeBgm; // 음악 볼륨
        public int volumeSfx; // 효과음 볼륨
        public int width; // 창모드일때만 활성화
        public int height; // 창모드일때만 활성화
        public int screenMode; // 0:창, 1:전체화면, 2:전체창(테두리 없음)
        public boolean shake; // 화면 흔들림
        public boolean fastMode; // 효과와 액션 배속
        public boolean charTutorial;
        public boolean wayTutorial;
        public boolean battleTutorial;
        public boolean rewardTutorial;
        public boolean forceCredit;
        public boolean[] cartoon;
        public HashMap<AbstractPlayer.PlayerClass, String> skin;
    }
}
