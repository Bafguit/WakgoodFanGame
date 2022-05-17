package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

public class SaveHandler {

    protected static final String[] randomKey = new String[] {"public", "skill", "item", "relic", "map", "monster", "event", "shop"};
    public static File saveFile = new File("save.json");
    public static SaveData data;
    public static boolean hasSave;

    public SaveHandler() {
        refresh();
    }

    public static void refresh() {
        hasSave = saveFile.exists();
    }

    public static void save() {
        data = new SaveData();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(saveFile, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void finish() {
        refresh();
        if(hasSave) {
            saveFile.delete();
        }

        Date now = new Date();
        System.out.println(now);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String formatted = formatter.format(now);

        data = new SaveData();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File("save_" + formatted + ".json"), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {

    }

    public static class SaveData {
        public RandomData random;
        public FloorData[] floors = new FloorData[4];
        public EntityData[] players = new EntityData[4];
        public AdvisorData advisor;
        public int currentFloor;
        public int uidCount;
        public int itemAble;
        public int selection;
        public int maxEnergy;
        public int energy;
        public int gold;

        public SaveData() {
            random = new RandomData();
            for(int i = 0; i < 4; i++) {
                AbstractFloor f = AbstractLabyrinth.floors[i];
                floors[i] = f != null ? new FloorData(f) : null;
            }
            currentFloor = AbstractLabyrinth.currentFloor.floorNum;
            for(int i = 0; i < 4; i++) {
                players[i] = new EntityData(AbstractLabyrinth.players[i]);
            }
            advisor = new AdvisorData(AbstractLabyrinth.advisor);
            uidCount = Labyrintale.getUid() - 1;
            itemAble = AbstractLabyrinth.itemAble;
            selection = AbstractLabyrinth.selection;
            maxEnergy = AbstractLabyrinth.maxEnergy;
            energy = AbstractLabyrinth.energy;
            gold = AbstractLabyrinth.gold;
        }
    }

    public static class RandomData {
        public String seed;
        public Long[] publicRandom;
        public Long[] skillRandom;
        public Long[] itemRandom;
        public Long[] relicRandom;
        public Long[] mapRandom;
        public Long[] monsterRandom;
        public Long[] eventRandom;
        public Long[] shopRandom;

        public RandomData() {
            seed = AbstractLabyrinth.seed;
            publicRandom = new Long[]{AbstractLabyrinth.publicRandom.getState(0), AbstractLabyrinth.publicRandom.getState(1)};
            skillRandom = new Long[]{AbstractLabyrinth.skillRandom.getState(0), AbstractLabyrinth.skillRandom.getState(1)};
            itemRandom = new Long[]{AbstractLabyrinth.itemRandom.getState(0), AbstractLabyrinth.itemRandom.getState(1)};
            relicRandom = new Long[]{AbstractLabyrinth.relicRandom.getState(0), AbstractLabyrinth.relicRandom.getState(1)};
            mapRandom = new Long[]{AbstractLabyrinth.mapRandom.getState(0), AbstractLabyrinth.mapRandom.getState(1)};
            monsterRandom = new Long[]{AbstractLabyrinth.monsterRandom.getState(0), AbstractLabyrinth.monsterRandom.getState(1)};
            eventRandom = new Long[]{AbstractLabyrinth.eventRandom.getState(0), AbstractLabyrinth.eventRandom.getState(1)};
            shopRandom = new Long[]{AbstractLabyrinth.shopRandom.getState(0), AbstractLabyrinth.shopRandom.getState(1)};
        }
    }

    public static class FloorData {
        public RoomData currentRoom;
        public WayData currentWay;
        public WayData[] ways = new WayData[13];
        public boolean canBoss;
        public int floorNum;
        public int num;

        public FloorData(AbstractFloor f) {
            currentRoom = f.currentRoom != null ? new RoomData(f.currentRoom) : null;
            currentWay = f.currentWay != null ? new WayData(f.currentWay) : null;
            for(int i = 0; i < 13; i++) {
                ways[i] = new WayData(f.ways[i]);
            }
            canBoss = f.canBoss;
            floorNum = f.floorNum;
            num = f.num;
        }
    }

    public static class WayData {
        public String type;
        public ChoiceData[] choices;

        public WayData(AbstractWay w) {
            type = w.type.toString();
            int l = w.choices.length;
            choices = new ChoiceData[l];
            for(int i = 0; i < l; i++) {
                choices[i] = new ChoiceData(w.choices[i]);
            }
        }
    }

    public static class ChoiceData {
        public String type;
        public RoomData room;
        public boolean must;
        public int prob;

        public ChoiceData(AbstractChoice c) {
            type = c.type.toString();
            room = new RoomData(c.room);
            must = c.must;
            prob = c.prob;
        }
    }

    public static class RoomData {
        public String id;

        public RoomData(AbstractRoom r) {
            id = r.id;
        }
    }

    public static class EntityData {
        public String id;
        public String[] item = new String[2];
        public Array<SkillData> deck = new Array<>();
        public boolean isPlayer;
        public boolean isDead;
        public int index;
        public int maxHealth;
        public int health;
        public int block;

        public EntityData(AbstractEntity e) {
            id = e.id;
            for(int i = 0; i < 2; i++) {
                AbstractItem t = e.item[i];
                item[i] = t != null ? t.id : null;
            }
            for(AbstractSkill s : e.deck) {
                deck.add(new SkillData(s));
            }
            isPlayer = e.isPlayer;
            isDead = e.isDead;
            index = e.index;
            maxHealth = e.maxHealth;
            health = e.health;
            block = e.block;
        }
    }

    public static class AdvisorData {
        public String cls;
        public SkillData skill;

        public AdvisorData(AbstractAdvisor a) {
            cls = a.cls.toString();
            skill = new SkillData(a.skill);
        }
    }

    public static class SkillData {
        public String id;
        public boolean upgraded;
        public boolean removable;
        public boolean usedOnce;
        public boolean usedOnly;
        public boolean passive;
        public boolean disposable;
        public boolean isTrick;
        public int upgradeCount;
        public int attack;
        public int upAttack;
        public int spell;
        public int upSpell;
        public int value;
        public int upValue;
        public int cooltime;

        public SkillData(AbstractSkill s) {
            id = s.id;
            upgraded = s.upgraded;
            removable = s.removable;
            usedOnce = s.usedOnce;
            usedOnly = s.usedOnly;
            passive = s.passive;
            disposable = s.disposable;
            isTrick = s.isTrick;
            upgradeCount = s.upgradeCount;
            attack = s.baseAttack;
            upAttack = s.upAttack;
            spell = s.baseSpell;
            upSpell = s.upSpell;
            value = s.baseValue;
            upValue = s.upValue;
            cooltime = s.cooltime;
        }
    }
}
