package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractPlayer.CustomSkinData;

import java.util.HashMap;

public class CustomHandler {

    public static final HashMap<AbstractPlayer.PlayerClass, HashMap<String, CustomSkinData>> skins = new HashMap<>();

    public static void initialize() {

        for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
            skins.put(cls, new HashMap<>());
        }

        /* TODO 스킨 추가되면 주석 지우기
        for(AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
            int i = AchieveHandler.achvs.get(AchieveHandler.Achievement.valueOf(cls.toString()));
            if(i == 3) {
                CustomSkinData data = new CustomSkinData(cls);
                skins.get(data.playerClass).put(data.key, data);
            }
        }*/

        FileHandle folder = Gdx.files.local("custom");
        if(folder.exists() && folder.isDirectory()) {
            for(FileHandle f : folder.list()) {
                if(f.isDirectory()) {
                    CustomSkinData data = new CustomSkinData(f.name());
                    skins.get(data.playerClass).put(data.key, data);
                }
            }
        } else {
            folder.mkdirs();
        }
    }

}
