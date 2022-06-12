package com.fastcat.labyrintale.handlers;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;

public class RestrictionHandler {

    public int GRW;
    public int STR;
    public int INT;
    public int HUG;
    public int FAM;
    public int FOG;
    public int POV;
    public int MSR;
    public int FTG;
    public int ANX;

    public RestrictionHandler() {
        reset();
    }

    public void reset() {
        GRW = 0;
        STR = 0;
        INT = 0;
        HUG = 0;
        FAM = 0;
        FOG = 0;
        POV = 0;
        MSR = 0;
        FTG = 0;
        ANX = 0;
    }

    public void setData(SaveHandler.RestrictionData data) {
        GRW = data.GRW;
        STR = data.STR;
        INT = data.INT;
        HUG = data.HUG;
        FAM = data.FAM;
        FOG = data.FOG;
        POV = data.POV;
        MSR = data.MSR;
        FTG = data.FTG;
        ANX = data.ANX;
    }

    public void onEnemySpawn(AbstractEnemy enemy) {

    }

    public void atBattleStart() {

    }

    public void atBattleEnd() {

    }
}
