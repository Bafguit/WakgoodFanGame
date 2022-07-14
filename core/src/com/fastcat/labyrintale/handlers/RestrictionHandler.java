package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.SpellStatus;

public class RestrictionHandler {

    public int GRW; //TODO
    public int STR; //TODO
    public int INT; //TODO
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

    public void onCreateLabyrinth() {
        if(POV == 1) AbstractLabyrinth.gold -= 10;
        else if(POV == 2) AbstractLabyrinth.gold -= 30;
        else if (POV == 3) AbstractLabyrinth.gold -= 50;

        if(FOG > 0) AbstractLabyrinth.bleak += FOG * 10;
        if(ANX > 0) AbstractLabyrinth.bleakAdd += ANX;
    }

    public void onCreatePlayer(AbstractPlayer player) {
        if(HUG > 0) player.setMaxHealth((int) (player.maxHealth * (1.0f - GRW * 0.1f)), true);
        if(FTG > 0) player.health -= FTG * 2;
    }

    public void onEnemySpawn(AbstractEnemy enemy) {
        if(GRW > 0) enemy.setMaxHealth((int) (enemy.maxHealth * (1.0f + GRW * 0.1f)), true);
    }

    public void atBattleStart() {
        if(STR == 1) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(AbstractSkill.SkillTarget.ENEMY_LAST_TWO);
            for(AbstractEntity e : temp) {
                e.applyStatus(new AttackStatus(1), 1, false);
            }
        } else if(STR == 2) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(AbstractSkill.SkillTarget.ENEMY_ALL);
            for(AbstractEntity e : temp) {
                e.applyStatus(new AttackStatus(1), 1, false);
            }
        } else if(STR == 3) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(AbstractSkill.SkillTarget.ENEMY_ALL);
            for(AbstractEntity e : temp) {
                e.applyStatus(new AttackStatus(2), 2, false);
            }
        }

        if(INT == 1) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(AbstractSkill.SkillTarget.ENEMY_FIRST_TWO);
            for(AbstractEntity e : temp) {
                e.applyStatus(new SpellStatus(1), 1, false);
            }
        } else if(INT == 2) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(AbstractSkill.SkillTarget.ENEMY_ALL);
            for(AbstractEntity e : temp) {
                e.applyStatus(new SpellStatus(1), 1, false);
            }
        } else if(INT == 3) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(AbstractSkill.SkillTarget.ENEMY_ALL);
            for(AbstractEntity e : temp) {
                e.applyStatus(new SpellStatus(2), 2, false);
            }
        }
    }

    public void atBattleEnd() {

    }

    public int onGainGoldReward(int gold) {
        if(FAM == 1) return (int)((float) gold * 0.9f);
        else if(FAM == 2) return (int)((float) gold * 0.7f);
        else if(FAM == 3) return (int)((float) gold * 0.5f);
        else return gold;
    }

    public int onCreateShopItem(int price) {
        if(MSR > 0) return (int)(price * (1.0f - MSR * 0.1f));
        else return price;
    }
}
