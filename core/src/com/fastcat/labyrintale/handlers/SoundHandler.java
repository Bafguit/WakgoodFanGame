package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class SoundHandler {

    public static final HashMap<String, Sound> sfx = new HashMap<>();
    public static final Array<Sound> sfxPlaylist = new Array<>();
    public static final Array<Sound> fadingSfx = new Array<>();

    public static final HashMap<String, MusicData> music = new HashMap<>();
    public static final Array<MusicData> musicPlaylist = new Array<>();
    public static final Array<MusicData> fadingMusic = new Array<>();

    public SoundHandler() {
        generateSound();
        generateMusic();
    }

    private static void generateSound() {
        sfx.put("LILPAA", FileHandler.LILPAA);
        sfx.put("ATTACK_TEST", FileHandler.ATTACK_TEST);
        sfx.put("CLICK", FileHandler.CLICK);
        sfx.put("OVER", FileHandler.OVER);
    }

    private static void generateMusic() {
        music.put("BATTLE_1", new MusicData(FileHandler.BATTLE_1));
    }

    public static void playSfx(String key) {
        Sound s = sfx.get(key);
        if(s != null) {
            s.play();
            sfxPlaylist.add(s);
        }
    }

    public static void playMusic(String key, float volume, boolean isLoop, boolean fadeIn) {
        MusicData d = music.get(key);
        if(d != null) {
            Music s = d.music;
            s.setLooping(isLoop);
            if (fadeIn) {
                d.isFadingOut = false;
                d.fadeOutStartVolume = volume;
                d.fadeTimer = 4.0F;
                s.setVolume(0.0f);
                fadingMusic.add(d);
            } else s.setVolume(volume);
            s.play();
            musicPlaylist.add(d);
        }
    }

    public static void fadeOutMusic(String key) {
        MusicData d = music.get(key);
        d.isFadingOut = true;
        d.fadeOutStartVolume = d.music.getVolume();
        d.fadeTimer = 4.0F;
        fadingMusic.add(d);
    }

    public void update() {
        for(MusicData data : fadingMusic) {
            if (!data.isFadingOut) {
                data.updateFadeIn();
            } else {
                data.updateFadeOut();
            }
        }

    }

    public static class MusicData {
        public float fadeTimer = 0.0F;
        public boolean isFadingOut = false;
        private float fadeOutStartVolume;
        public boolean isDone = false;
        public Music music;

        public MusicData(Music music) {
            this.music = music;
        }

        public void updateFadeIn() {
            fadeTimer -= Gdx.graphics.getDeltaTime();
            if (fadeTimer < 0.0F) {
                fadeTimer = 0.0F;
                fadingMusic.removeValue(this, false);
            }
            music.setVolume(Interpolation.fade.apply(0.0F, fadeOutStartVolume, 1.0F - fadeTimer / 4.0F));
        }

        public void updateFadeOut() {
            fadeTimer -= Gdx.graphics.getDeltaTime();
            if (fadeTimer < 0.0F) {
                fadeTimer = 0.0F;
                isDone = true;
                music.stop();
                fadingMusic.removeValue(this, false);
                musicPlaylist.removeValue(this, false);
            } else {
                music.setVolume(Interpolation.fade.apply(fadeOutStartVolume, 0.0F, 1.0F - fadeTimer / 4.0F));
            }
        }
    }
}
