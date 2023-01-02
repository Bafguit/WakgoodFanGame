package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
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

    public static File settingFile;

    public static void initialize(boolean android) {
        setting = new SettingData();
        if(android) settingFile = Gdx.files.local("setting.json").file();
        else settingFile = new File("setting.json");
        boolean hasSave = settingFile.exists();
        if (!hasSave) {

            // 화면 모드 설정
            setting.screenMode = 0;
            setting.width = 1600;
            setting.height = 900;

            // 볼륨 설정
            setting.volumeBgm = 80;
            setting.volumeSfx = 80;

            // 기타 설정
            setting.shake = true;
            setting.fastMode = false;

            // 튜토리얼
            setting.charTutorial = true;
            setting.battleTutorial = true;
            setting.wayTutorial = true;
            setting.rewardTutorial = true;

            setting.cartoon = new boolean[3];
            for(int i = 0; i < 3; i++) {
                setting.cartoon[i] = true;
            }
            setting.forceCredit = true;

            setting.skin = new HashMap<>();
            for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
                setting.skin.put(cls, "basic");
            }
        } else {
            try {
                SettingData data = mapper.readValue(settingFile, SettingData.class);

                // 화면 모드 설정
                setting.screenMode = data.screenMode;
                if (setting.screenMode == 0) {
                    setting.width = data.width;
                    setting.height = data.height;
                }

                // 볼륨 설정
                setting.volumeBgm = data.volumeBgm;
                setting.volumeSfx = data.volumeSfx;

                // 기타 설정
                setting.shake = data.shake;
                setting.fastMode = data.fastMode;

                // 튜토리얼
                setting.charTutorial = data.charTutorial;
                setting.battleTutorial = data.battleTutorial;
                setting.rewardTutorial = data.rewardTutorial;
                setting.wayTutorial = data.wayTutorial;


                setting.cartoon = new boolean[3];
                System.arraycopy(data.cartoon, 0, setting.cartoon, 0, 3);
                setting.forceCredit = data.forceCredit;

                if(data.skin != null) {
                    setting.skin = data.skin;
                } else {
                    setting.skin = new HashMap<>();
                    for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
                        setting.skin.put(cls, "basic");
                    }
                }
            } catch (IOException e) {
                hasSave = false;
            }
        }
    }

    public static void save() {
        try {
            mapper.writeValue(Gdx.files.local("setting.json").file(), setting);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
