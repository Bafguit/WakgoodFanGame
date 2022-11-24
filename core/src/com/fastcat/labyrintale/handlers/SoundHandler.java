package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;

import java.util.HashMap;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public final class SoundHandler implements Disposable {

  public static final HashMap<String, Sound> sfx = new HashMap<>();
  public static final HashMap<String, MusicData> music = new HashMap<>();
  public static final Queue<MusicData> nextMusic = new Queue<>();
  private static final float FADE = 2.5f;
  private static final float MASTER_M = 0.4f;
  private static final float MASTER_S = 0.8f;
  public static MusicData curMusic;

  /***
   * Instance of handler.
   * Initialized on getInstance()
   */
  private static SoundHandler instance;

  private SoundHandler() {
    generateSound();
    generateMusic();
  }

  /***
   * Returns instance of handler, if not exist, create new.
   * @return instance of handler
   */
  public static SoundHandler getInstance() {
    if (instance == null) return (instance = new SoundHandler());
    return instance;
  }

  private static void generateSound() {
    sfx.put("LILPAA", getSound("sound/sfx/lilpaa.ogg"));
    sfx.put("BLOCK", getSound("sound/sfx/block.ogg"));
    sfx.put("BLUNT_HEAVY", getSound("sound/sfx/blunt_heavy.ogg"));
    sfx.put("BLUNT_LIGHT", getSound("sound/sfx/blunt_light.ogg"));
    sfx.put("BUFF", getSound("sound/sfx/buff.ogg"));
    sfx.put("BURN", getSound("sound/sfx/burn.ogg"));
    sfx.put("CLICK", getSound("sound/sfx/click.ogg"));
    sfx.put("DEBUFF", getSound("sound/sfx/debuff.ogg"));
    sfx.put("HEAL", getSound("sound/sfx/heal.ogg"));
    sfx.put("LIGHTNING", getSound("sound/sfx/lightning.ogg"));
    sfx.put("POISON", getSound("sound/sfx/poison.ogg"));
    sfx.put("SLASH", getSound("sound/sfx/slash.ogg"));
    sfx.put("SMASH", getSound("sound/sfx/smash.ogg"));
    sfx.put("GUN", getSound("sound/sfx/gun.ogg"));
    sfx.put("STATIC", getSound("sound/sfx/static.ogg"));
  }

  private static void generateMusic() {
    String s;
    music.put(s = "BATTLE_1", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "BATTLE_2", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "BATTLE_3", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "BATTLE_4", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "BOSS_1", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "BOSS_2", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "BOSS_3", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "BOSS_4", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "WAY_1", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "WAY_2", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "WAY_3", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put(s = "WAY_4", new MusicData(s, getMusic("sound/bgm/" + s.toLowerCase() + ".mp3")));
    music.put("LOBBY", new MusicData("LOBBY", getMusic("sound/bgm/demo.mp3")));
    music.put("WIN", new MusicData("LOBBY", getMusic("sound/bgm/win.mp3")));
    music.put("LOSS", new MusicData("LOBBY", getMusic("sound/bgm/loss.mp3")));
    // music.put("LOGO", new MusicData("LOGO", getMusic("sound/bgm/logo.mp3")));
  }

  private static Sound getSound(String url) {
    FileHandle fileHandle = Gdx.files.internal(url);
    return Gdx.audio.newSound(fileHandle);
  }

  private static Music getMusic(String url) {
    FileHandle fileHandle = Gdx.files.internal(url);
    return Gdx.audio.newMusic(fileHandle);
  }

  public static Sound playSfx(String key) {
    Sound s = sfx.get(key);
    if (s != null) {
      s.play(sVol());
    }
    return s;
  }

  public static MusicData playMusic(String key, boolean isLoop, boolean fadeIn) {
    if (curMusic != null && curMusic.key.equals(key)) return curMusic;
    else {
      MusicData d = music.get(key);
      if (d != null) {
        Music s = d.music;
        s.setLooping(isLoop);
        if (fadeIn) {
          d.isFadingOut = false;
          d.isFading = true;
          d.isDone = false;
          d.fadeOutStartVolume = mVol();
          d.setFadeTime(FADE);
          s.setVolume(0.0f);
        } else s.setVolume(mVol());
        curMusic = d;
        s.play();
      }
      return d;
    }
  }

  public static MusicData addWay() {
    return addMusic("WAY_" + AbstractLabyrinth.floorNum, true, true);
  }

  public static MusicData addBattle() {
    if(currentFloor.currentRoom.type == BATTLE || currentFloor.currentRoom.type == ELITE) {
      return addMusic("BATTLE_" + AbstractLabyrinth.floorNum, true, true);
    } else {
      return addMusic("BOSS_" + AbstractLabyrinth.floorNum, true, true);
    }
  }

  public static MusicData addMusic(String key, boolean isLoop, boolean fadeIn) {
    if (curMusic != null && curMusic.key.equals(key)) return curMusic;
    else {
      MusicData d = music.get(key);
      if (d != null) {
        Music s = d.music;
        s.setLooping(isLoop);
        if (fadeIn) {
          d.isFadingOut = false;
          d.isFading = true;
          d.isDone = false;
          d.fadeOutStartVolume = mVol();
          d.setFadeTime(FADE);
          s.setVolume(0.0f);
        } else s.setVolume(mVol());
        nextMusic.addLast(d);
      }
      return d;
    }
  }

  public static void fadeOutAll() {
    for(MusicData d : music.values()) {
      if (d != null && d.music.isPlaying()) {
        d.isFadingOut = true;
        d.isFading = true;
        d.fadeOutStartVolume = d.music.getVolume();
        d.setFadeTime(FADE);
      }
    }
  }

  public static void fadeOutMusic(String key) {
    MusicData d = music.get(key);
    if (d != null) {
      d.isFadingOut = true;
      d.isFading = true;
      d.fadeOutStartVolume = d.music.getVolume();
      d.setFadeTime(FADE);
    }
  }

  public static float mVol() {
    return SettingHandler.setting.volumeBgm * 0.01f * MASTER_M;
  }

  public static float sVol() {
    return SettingHandler.setting.volumeSfx * 0.01f * MASTER_S;
  }

  public void update() {
    if (curMusic != null) {
      curMusic.update();
      if (curMusic.isDone) {
        if (nextMusic.size > 0) {
          curMusic = nextMusic.removeFirst();
          curMusic.music.play();
        } else curMusic = null;
      }
    } else if (nextMusic.size > 0) {
      curMusic = nextMusic.removeFirst();
      curMusic.music.play();
    }
  }

  @Override
  public void dispose() {
    for (Sound s : sfx.values()) {
      s.dispose();
    }
    for (MusicData m : music.values()) {
      m.dispose();
    }
  }

  public static class MusicData implements Disposable {
    public String key;
    public float fadeTime = FADE;
    public float fadeTimer = 0.0F;
    public boolean isFadingOut = false;
    public boolean isFading = false;
    public boolean isDone = false;
    public boolean stop = true;
    public Music music;
    private float fadeOutStartVolume;

    public MusicData(String key, Music music) {
      this.music = music;
      this.key = key;
    }

    public void update() {
      if (music.isPlaying()) {
        if (!isFading) {
          music.setVolume(mVol());
        } else {
          if (!isFadingOut) {
            updateFadeIn();
          } else {
            updateFadeOut();
          }
        }
      } else isDone = true;
    }

    public void setFadeTime(float time) {
      fadeTime = fadeTimer = time;
    }

    public void updateFadeIn() {
      fadeTimer -= Labyrintale.tick * 1.5;
      if (fadeTimer <= 0.0F) {
        fadeTimer = 0.0F;
        isFading = false;
      } else {
        setVolume(Interpolation.fade.apply(0.0F, fadeOutStartVolume, 1.0F - fadeTimer / fadeTime));
      }
    }

    public void updateFadeOut() {
      fadeTimer -= Labyrintale.tick * 1.5;
      if (fadeTimer <= 0.0F) {
        fadeTimer = 0.0F;
        isDone = true;
        isFading = false;
        if (stop) music.stop();
        else music.pause();
      } else {
        setVolume(Interpolation.fade.apply(fadeOutStartVolume, 0.0F, 1.0F - fadeTimer / fadeTime));
      }
    }

    public void setVolume(float volume) {
      if (volume < 0f || volume > 1f) {
        // Gdx.app.error("SoundHandler", "Volume not valid: " + volume);
        volume = Math.max(0, Math.min(volume, 1));
      }
      music.setVolume(volume);
    }

    @Override
    public void dispose() {
      music.dispose();
    }
  }
}
