package com.fastcat.labyrintale.handlers;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.Difficulty.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.diff;

import com.fastcat.labyrintale.abstracts.*;

public final class DifficultyHandler {

    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static DifficultyHandler instance;

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static DifficultyHandler getInstance() {
        if (instance == null) return (instance = new DifficultyHandler());
        return instance;
    }

    public void onCreateLabyrinth() {
        if (diff == HARD) AbstractLabyrinth.gold -= 15;
        else if (diff == COFFIN) AbstractLabyrinth.gold -= 30;
    }

    public void onCreatePlayer(AbstractPlayer player) {
        if (diff == HARD) {
            player.setMaxHealth((int) ((float) player.maxHealth * 0.9f), true);
            player.health = player.maxHealth - 2;
        } else if (diff == COFFIN) {
            player.setMaxHealth((int) ((float) player.maxHealth * 0.8f), true);
            player.health = player.maxHealth - 3;
        }
    }

    public void onEnemySpawn(AbstractEntity enemy) {
        if (diff == HARD) {
            enemy.setMaxHealth((int) ((float) enemy.maxHealth * 1.15f), true);
            enemy.stat.speed += 1;
            enemy.stat.moveRes += 10;
            enemy.stat.debuRes += 10;
            enemy.stat.neutRes += 10;
            enemy.stat.attack += 1;
            enemy.stat.spell += 1;
        } else if (diff == COFFIN) {
            enemy.setMaxHealth((int) ((float) enemy.maxHealth * 1.3f), true);
            enemy.stat.speed += 2;
            enemy.stat.moveRes += 20;
            enemy.stat.debuRes += 20;
            enemy.stat.neutRes += 20;
            enemy.stat.attack += 2;
            enemy.stat.spell += 2;
        }
    }

    public void atBattleStart() {}

    public void atBattleEnd() {}

    public int onGainGoldReward(int gold) {
        if (diff == HARD) return (int) ((float) gold * 0.9f);
        else if (diff == COFFIN) return (int) ((float) gold * 0.7f);
        else return gold;
    }

    public int onCreateShopItem(int price) {
        if (diff == HARD) return (int) ((float) price * 1.1f);
        else if (diff == COFFIN) return (int) ((float) price * 1.3f);
        else return price;
    }
}
