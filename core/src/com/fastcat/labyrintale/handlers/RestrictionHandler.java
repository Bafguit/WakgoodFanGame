package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.SpellStatus;

public final class RestrictionHandler {

    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static RestrictionHandler instance;
    public int GRW; //TODO
    public int STR; //TODO
    public int INT; //TODO
    public int HUG;
    public int FAM;
    public int SPD;
    public int POV;
    public int MSR;
    public int FTG;
    public int RST;

    private RestrictionHandler() {
        reset();
    }

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static RestrictionHandler getInstance() {
        if (instance == null)
            return (instance = new RestrictionHandler());
        return instance;
    }

    public void reset() {
        GRW = 0;
        STR = 0;
        INT = 0;
        HUG = 0;
        FAM = 0;
        SPD = 0;
        POV = 0;
        MSR = 0;
        FTG = 0;
        RST = 0;
    }

    public void setSetting() {
        GRW = SettingHandler.setting.risk.get(RiskType.GRW);
        STR = SettingHandler.setting.risk.get(RiskType.STR);
        INT = SettingHandler.setting.risk.get(RiskType.INT);
        HUG = SettingHandler.setting.risk.get(RiskType.HUG);
        FAM = SettingHandler.setting.risk.get(RiskType.FAM);
        SPD = SettingHandler.setting.risk.get(RiskType.SPD);
        POV = SettingHandler.setting.risk.get(RiskType.POV);
        MSR = SettingHandler.setting.risk.get(RiskType.MSR);
        FTG = SettingHandler.setting.risk.get(RiskType.FTG);
        RST = SettingHandler.setting.risk.get(RiskType.RST);
    }

    public void setData(SaveHandler.RestrictionData data) {
        GRW = data.GRW;
        STR = data.STR;
        INT = data.INT;
        HUG = data.HUG;
        FAM = data.FAM;
        SPD = data.SPD;
        POV = data.POV;
        MSR = data.MSR;
        FTG = data.FTG;
        RST = data.RST;
    }

    public void onCreateLabyrinth() {
        if (POV > 0) AbstractLabyrinth.gold -= 10 * POV;
    }

    public void onCreatePlayer(AbstractPlayer player) {
        if (HUG > 0) player.setMaxHealth((int) (player.maxHealth * (1.0f - GRW * 0.05f)), true);
        if (FTG > 0) player.health -= FTG;
    }

    public void onEnemySpawn(AbstractEnemy enemy) {
        if (GRW > 0) enemy.setMaxHealth((int) (enemy.maxHealth * (1.05f + GRW * 0.05f)), true);
        if (SPD > 0) enemy.stat.speed += 1 + SPD;
        if (RST > 0) {
            enemy.stat.moveRes += 0.05f * RST;
            enemy.stat.debuRes += 0.05f * RST;
            enemy.stat.neutRes += 0.05f * RST;
        }

        if (INT > 0) {
            enemy.stat.spell += INT;
        }
    }

    public void atBattleStart() {
        if (STR == 1) {
            for (AbstractEntity e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                e.stat.critical += 0.1f;
            }
        } else if (STR == 2) {
            for (AbstractEntity e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                e.stat.critical += 0.1f;
                e.stat.attack += 1;
            }
        } else if (STR == 3) {
            for (AbstractEntity e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                e.stat.critical += 0.1f;
                e.stat.multiply += 0.1f;
                e.stat.attack += 1;
            }
        }
    }

    public void atBattleEnd() {

    }

    public int onGainGoldReward(int gold) {
        if (FAM > 0) return (int) ((float) gold * (1 - 0.1f * FAM));
        else return gold;
    }

    public int onCreateShopItem(int price) {
        if (MSR > 0) return (int) (price * (1.0f + MSR * 0.1f));
        else return price;
    }

    public enum RiskType {
        GRW, STR, INT, SPD, RST, MSR, FAM, POV, FTG, HUG
    }
}
