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

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.Difficulty.COFFIN;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.Difficulty.HARD;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.diff;

public final class RunHandler {

  public static Array<SaveHandler.SaveData> runs = new Array<>();

  public static void load() {
    SaveHandler.refresh();
    runs.clear();
    if(SaveHandler.hasRuns) {
      FileHandle f = Gdx.files.local("runs.json");
      JsonValue js = FileHandler.generateJson(f);
      for(String name : js.asStringArray()) {
        FileHandle ff = Gdx.files.local("runs/" + name);
        if(ff.exists()) {
          try {
            SaveHandler.SaveData sd = SaveHandler.mapper.readValue(new File("runs/" + name), SaveHandler.SaveData.class);
            if(sd != null) runs.add(sd);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
