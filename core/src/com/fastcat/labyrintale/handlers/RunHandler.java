package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import org.lwjgl.Sys;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.Difficulty.COFFIN;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.Difficulty.HARD;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.diff;

public final class RunHandler {
  private static final Pattern DATE = Pattern.compile("run_[0-9]{4}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}.json");

  public static Array<SaveHandler.SaveData> runs = new Array<>();

  public static void load() {
    SaveHandler.refresh();
    runs.clear();
    File dir = new File("runs");
    if(dir.isDirectory()) {
      File[] rs = dir.listFiles();
      if(rs != null && rs.length > 0) {
        for(int i = rs.length - 1; i >= 0; i--) {
          File f = rs[i];
          String n = f.getName();
          Matcher matcher = DATE.matcher(n);
          if(matcher.find()) {
            try {
              SaveHandler.SaveData sd = SaveHandler.mapper.readValue(f, SaveHandler.SaveData.class);
              if(sd != null) runs.add(sd);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }
}
